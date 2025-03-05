package com.acl.mswauth.servicesImpl.port;

import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.model.*;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ClientRepository;
import com.acl.mswauth.repositories.PortClientRepository;
import com.acl.mswauth.repositories.UserClientRepository;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.request.PortClientModelList;
import com.acl.mswauth.service.AuthService;
import com.acl.mswauth.service.ConnectedUserService;
import com.acl.mswauth.service.port.PortUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/22/24
 * @project msw-auth
 */
@Service
public class PortUserServiceImpl implements PortUserService {

    private final Logger logger = LoggerFactory.getLogger(PortUserServiceImpl.class);

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final UserClientRepository userClientRepository;
    private final PortClientRepository portClientRepository;
    private final ConnectedUserService connectedUserService;

    @Autowired
    private AuthService authService;

    public PortUserServiceImpl(UserRepository userRepository, ClientRepository clientRepository, UserClientRepository userClientRepository, PortClientRepository portClientRepository, ConnectedUserService connectedUserService) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.userClientRepository = userClientRepository;
        this.portClientRepository = portClientRepository;
        this.connectedUserService = connectedUserService;
    }

    @Override
    public ResponseEntity<?> getPortUserClient() {
        Optional<User> user = userRepository.findByEmail(authService.currentUserEmail());
        logger.info("user present dans le port {} " ,user.isPresent());
        if(user.isPresent()) {
            List<UserClient> userClients = userClientRepository.findByUserId(user.get().getId());

            if(!userClients.isEmpty()) {

                logger.info("user client {}", userClients.size());

                Optional<MswClient> mswClient = clientRepository.findByCompteClient(userClients.get(0).getMswClient().getCompteClient());
                if(mswClient.isPresent()) {
                    List<PortClientModelList> ports = new ArrayList<>();

                    List<MswPortClient> mswPortClient = portClientRepository.findByClient(mswClient.get().getCompteClient());

                    MswConnectedUserDto mswConnectedUserDto = new MswConnectedUserDto(userClients.get(0).getMswClient().getCompteClient(),
                            user.get().getLogin(), user.get().getFullname(),
                            mswPortClient.get(0).getCodePortClient(), mswClient.get().getMswPays().getCode(),
                            mswClient.get().getNomClient());
                    MswConnectedUser currentConnected=connectedUserService.save(mswConnectedUserDto);
                    logger.info("User current session {}", currentConnected.getSessionId() );

                    // Information de L'utilisateur connecter connexion

                    mswPortClient.forEach(mspc -> {
                        PortClientModelList portClientModelList = new PortClientModelList();
                        portClientModelList.setCodePort(mspc.getCodePortClient());
                        portClientModelList.setCodeClient(mspc.getMswClient().getCompteClient());
                        portClientModelList.setNomClient(mspc.getMswClient().getNomClient());
                        portClientModelList.setPays(mspc.getMswPort().getMswPays().getNom());
                        portClientModelList.setNomPort(mspc.getMswPort().getNom());
                        portClientModelList.setCodePays(mspc.getMswPort().getMswPays().getCode());
                        portClientModelList.setLoginName(mswConnectedUserDto.getUserLogin());
                        portClientModelList.setSessionId(currentConnected.getSessionId());
                        ports.add(portClientModelList);
                    });

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                            "Opération réussi ",ports),HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            "Le client avec utilisateur n'existe pas ",authService.currentUserEmail()),HttpStatus.OK);
                }
            }
            else {
                Optional<User> mswUser = userRepository.findOneStructure(user.get().getMswStructure().getId(), user.get().getEmail());
                logger.info("User apartenant à une structure {}", mswUser.isPresent());
                if (mswUser.isPresent()) {

                    List<PortClientModelList> ports = new ArrayList<>();

                    List<MswPortClient> mswPortClient = portClientRepository.findByStructureId(mswUser.get().getMswStructure().getId());

                    //Structure connecter

                    MswConnectedUserDto mswConnectedUserDto = new MswConnectedUserDto(mswUser.get().getLogin(),
                            mswUser.get().getLogin(), mswUser.get().getFullname(),
                            mswPortClient.get(0).getMswPort().getCode(), mswPortClient.get(0).getMswPort().getMswPays().getCode(),
                            mswUser.get().getMswStructure().getNom());
                    MswConnectedUser currentConnected=connectedUserService.save(mswConnectedUserDto);


                    logger.info("User current session {}", currentConnected.getSessionId() );

                    //Afficher les information de l'utilisateur connecter

                    mswPortClient.forEach(mspc -> {
                        PortClientModelList portClientModelList = new PortClientModelList();
                        portClientModelList.setCodePort(mspc.getCodePortClient());
                        portClientModelList.setCodeClient(mspc.getMswStructure().getNom());
                        portClientModelList.setNomClient(mspc.getMswStructure().getNom());
                        portClientModelList.setPays(mspc.getMswPort().getMswPays().getNom());
                        portClientModelList.setNomPort(mspc.getMswPort().getNom());
                        portClientModelList.setCodePays(mspc.getMswPort().getMswPays().getCode());
                        portClientModelList.setLoginName(mswConnectedUserDto.getUserLogin());
                        portClientModelList.setSessionId(currentConnected.getSessionId());
                        ports.add(portClientModelList);
                    });

                    logger.info("Information Port {} ", ports);

                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                            "Opération réussi ",ports),HttpStatus.OK);

                }else  {
                    return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                            "Le client avec utilisateur n'existe pas ",authService.currentUserEmail()),HttpStatus.OK);
                }

            }

        }
        else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Email n'existe pas ",authService.currentUserEmail()),HttpStatus.OK);
        }
    }


    public ResponseEntity<?> getUserClient(List<UserClient> userClients, Optional<User> user) {
        Optional<MswClient> mswClient = clientRepository.findByCompteClient(userClients.get(0).getMswClient().getCompteClient());
        if (mswClient.isPresent()) {
            List<PortClientModelList> ports = new ArrayList<>();

            List<MswPortClient> mswPortClient = portClientRepository.findByClient(mswClient.get().getCompteClient());

            MswConnectedUserDto mswConnectedUserDto = new MswConnectedUserDto(userClients.get(0).getMswClient().getCompteClient(),
                    user.get().getLogin(), user.get().getFullname(),
                    mswPortClient.get(0).getCodePortClient(), mswClient.get().getMswPays().getCode(),
                    mswClient.get().getNomClient());
            MswConnectedUser currentConnected = connectedUserService.save(mswConnectedUserDto);
            logger.info("User current session {}", currentConnected.getSessionId());

            // Information de L'utilisateur connecter connexion

            mswPortClient.forEach(mspc -> {
                PortClientModelList portClientModelList = new PortClientModelList();
                portClientModelList.setCodePort(mspc.getCodePortClient());
                portClientModelList.setCodeClient(mspc.getMswClient().getCompteClient());
                portClientModelList.setNomClient(mspc.getMswClient().getNomClient());
                portClientModelList.setPays(mspc.getMswPort().getMswPays().getNom());
                portClientModelList.setNomPort(mspc.getMswPort().getNom());
                portClientModelList.setCodePays(mspc.getMswPort().getMswPays().getCode());
                portClientModelList.setLoginName(mswConnectedUserDto.getUserLogin());
                portClientModelList.setSessionId(currentConnected.getSessionId());
                ports.add(portClientModelList);
            });

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Opération réussi ", ports), HttpStatus.OK);
        }else {
            return null;
        }
    }

}
