package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.dto.user.DetailUserDto;
import com.acl.mswauth.dto.user.UserListDto;
import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.playload.ApiResponseMessage;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.KeycloakUserService;
import com.acl.mswauth.service.user.UserListService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@Slf4j
public class UserListServiceImpl implements UserListService {


    private final KeycloakUserService keycloakUserService;

    private final UserRepository userRepository;

    @Autowired
    private Environment env;


    public UserListServiceImpl(KeycloakUserService keycloakUserService, UserRepository userRepository) {
        this.keycloakUserService = keycloakUserService;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<?> getAllUserActiveList(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Page<UserInterface> userInterfaceList = userRepository.findAllUsersActive(pageable);
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.application.sucess"),
                userInterfaceList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllActiveList() {
        List<UserInterface> userInterfaceList = userRepository.findAllListUsersActive();

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.application.sucess"),
                userInterfaceList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByIdUser(Long request) {
        List<UserInterface> userInterfaceList = userRepository.findAllListUsersActive();

       Optional<UserInterface> userInterface = userInterfaceList.stream().
                filter(c-> c.getIdUser().equals(request)).findFirst();

       if (userInterface.isPresent()) {

           UsersResource usersResource = keycloakUserService.getUsersResource();
           List<UserRepresentation> user = usersResource.search(userInterface.get().getLogin(), true);
           log.info("user present {}", userInterface.get().getLogin());

           if (!user.isEmpty()) {
               DetailUserDto detailUserDto = new DetailUserDto(userInterface.get().getIdUser(),user.get(0).getUsername(),
                       user.get(0).getEmail(), userInterface.get().getNomEntreprise(), userInterface.get().getTelephone(),
                       userInterface.get().getFullName(), user.get(0).getLastName(), user.get(0).getFirstName(), userInterface.get().getFonction(),
                       userInterface.get().getGroupe(), userInterface.get().getCompteClient());
               return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                       detailUserDto), HttpStatus.OK);
           }else  {
                return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                       env.getProperty("message.application.notfound")
               ), HttpStatus.OK);
           }
       } else {
           return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                   env.getProperty("message.application.notfound")
                   ), HttpStatus.OK);
       }

    }

    @Override
    public ResponseEntity<?> findByProfil(String currentUserName) {

        log.info("user Curent user name {}", currentUserName);

        List<UserInterface> userInterfaceList = userRepository.findAllListUsersActive();

        Optional<UserInterface> userInterface = userInterfaceList.stream().
                filter(c-> c.getEmail().equals(currentUserName)).findFirst();

        log.info("user present {}", currentUserName);

        if (userInterface.isPresent()) {

            UsersResource usersResource = keycloakUserService.getUsersResource();
            List<UserRepresentation> user = usersResource.search(userInterface.get().getLogin(), true);
            log.info("user present {}", userInterface.get().getLogin());
            if (!user.isEmpty()) {
                DetailUserDto detailUserDto = new DetailUserDto(userInterface.get().getIdUser(),user.get(0).getUsername(),
                        user.get(0).getEmail(), userInterface.get().getNomEntreprise(), userInterface.get().getTelephone(),
                        userInterface.get().getFullName(), user.get(0).getLastName(), user.get(0).getFirstName(), userInterface.get().getFonction(),
                        userInterface.get().getGroupe(), userInterface.get().getCompteClient());
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                        detailUserDto), HttpStatus.OK);
            } else  {
                return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.application.notfound.user")
                ), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new ApiResponseMessage(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.notfound")
            ), HttpStatus.OK);
        }
    }


}
