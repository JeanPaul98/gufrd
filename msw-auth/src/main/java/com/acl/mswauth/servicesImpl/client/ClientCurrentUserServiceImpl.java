package com.acl.mswauth.servicesImpl.client;

import com.acl.mswauth.dto.ClientDto;
import com.acl.mswauth.model.User;
import com.acl.mswauth.model.UserClient;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.UserClientRepository;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.AuthService;
import com.acl.mswauth.service.client.ClientCurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/29/24
 * @project msw-auth
 */
@Service
public class ClientCurrentUserServiceImpl implements ClientCurrentUserService {

    Logger logger = LoggerFactory.getLogger(ClientCurrentUserServiceImpl.class);

    private final UserClientRepository userClientRepository;


    @Autowired
    private Environment env;

    private final UserRepository userRepository;

    private final AuthService authService;

    public ClientCurrentUserServiceImpl(UserClientRepository userClientRepository, UserRepository userRepository, AuthService authService) {
        this.userClientRepository = userClientRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public ResponseEntity<?> getAllClientByUser() {
        logger.info("utilisateur connecté {}", authService.currentUserEmail());

            Optional<User> user = userRepository.findByEmail(authService.currentUserEmail());

            if(user.isPresent()) {
                if(user.get().getMswStructure()!=null) {
                    ClientDto clientStructure = new ClientDto();
                    clientStructure.setNomClient(user.get().getMswStructure().getNom());
                    clientStructure.setCodeClient(user.get().getMswStructure().getNom());
                    clientStructure.setStructure(user.get().getMswStructure().getNom());
                    clientStructure.setCodePays(user.get().getMswStructure().getMswPays().getCode());
                    clientStructure.setId(user.get().getMswStructure().getId());
                    clientStructure.setClientAdresse(user.get().getMswStructure().getAdresse());
                    List<ClientDto> clientDtostructure = new ArrayList<>();
                    clientDtostructure.add(clientStructure);

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.application.sucess"),
                            clientDtostructure), HttpStatus.OK);
                } else {
                    List<UserClient> userClients = userClientRepository.findByUserId(user.get().getId());
                    List<ClientDto> clientDtos = new ArrayList<>();
                    for(UserClient uc : userClients) {
                        ClientDto clientDto = new ClientDto(uc.getMswClient().getNomClient(), uc.getMswClient().getCompteClient(),
                                uc.getMswClient().getClientAdresse(), uc.getMswClient().getNif(), uc.getMswClient().getMswPays().getCode());
                        clientDtos.add(clientDto);
                    }
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.application.sucess"),
                            clientDtos), HttpStatus.OK);
                }

            }
            else  {
                return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.notfound.user")), HttpStatus.OK);
            }
        }
}
