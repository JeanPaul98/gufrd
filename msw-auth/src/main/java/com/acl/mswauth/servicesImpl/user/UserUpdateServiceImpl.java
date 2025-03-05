package com.acl.mswauth.servicesImpl.user;

import com.acl.mswauth.dto.register.RegisterDto;
import com.acl.mswauth.dto.register.RegisterUpdateDto;
import com.acl.mswauth.exception.UserNotFoundException;
import com.acl.mswauth.model.User;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.repositories.UserRepository;
import com.acl.mswauth.service.KeycloakUserService;
import com.acl.mswauth.service.user.UserUpdateService;
import jakarta.transaction.Transactional;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/24/24
 * @project msw-auth
 */
@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserUpdateServiceImpl.class);

    private final KeycloakUserService keycloakUserService;

    @Autowired
    private Environment env;

    public UserUpdateServiceImpl(UserRepository userRepository, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.keycloakUserService = keycloakUserService;
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(RegisterUpdateDto request) {
        Optional<User> userOptional = userRepository.findById(request.getIdUser());

        if(userOptional.isPresent()) {

            logger.info("Utilisateur old Login {}",userOptional.get().getLogin());

            keycloakUpdate(userOptional.get().getLogin(),request.getLogin(), request.getEmail(), request.getFirstName(),
                    request.getLastName(), request.getPassword());

            userOptional.get().setLogin(request.getLogin());
            userOptional.get().setEmail(request.getEmail());
            userOptional.get().setFullname(request.getFirstName()+" "+request.getLastName());
            userOptional.get().setUpdatedAt(new Date());
            userRepository.save(userOptional.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess") ,request),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.notfound") ,request),HttpStatus.OK);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> updateProfil(RegisterUpdateDto request) {
        Optional<User> userOptional = userRepository.findById(request.getIdUser());

        if(userOptional.isPresent()) {

            logger.info("Utilisateur old Login {}",userOptional.get().getLogin());

            keycloakUpdateUser(userOptional.get().getLogin(),request.getLogin(), request.getEmail(), request.getFirstName(),
                    request.getLastName(), request.getPassword());

            userOptional.get().setLogin(request.getLogin());
            userOptional.get().setEmail(request.getEmail());
            userOptional.get().setFullname(request.getFirstName()+" "+request.getLastName());
            userOptional.get().setUpdatedAt(new Date());
            userRepository.save(userOptional.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess") ,request),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.application.notfound") ,request),HttpStatus.OK);
        }

    }


    private void keycloakUpdate(String login, String username, String email, String firstName, String lastName,
                                String password) {
        UsersResource usersResource = keycloakUserService.getUsersResource();
        List<UserRepresentation> user = usersResource.search(login, true);
        if(!user.isEmpty()) {

            UserRepresentation userRepresentation = user.get(0);
            usersResource.get(userRepresentation.getId()).remove();
            logger.info("Utilisateur supprimée avec sucès {}",login);

            UserRegistrationRecord userRegistrationRecord = new UserRegistrationRecord(username,email,firstName,
                    lastName, password);

            keycloakUserService.createUser(userRegistrationRecord);
            logger.info("Utilisateur créer avec sucès {}",username);

        } else {
            throw  new UserNotFoundException("User not found dans keycloak");
        }

    }

    private void keycloakUpdateUser(String login, String username, String email, String firstName, String lastName,
                                String password) {
        UsersResource usersResource = keycloakUserService.getUsersResource();
        List<UserRepresentation> user = usersResource.search(login, true);
        if(!user.isEmpty()) {

            UserRepresentation userRepresentation = user.get(0);
            userRepresentation.setLastName(lastName);
            userRepresentation.setFirstName(firstName);

            CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
            credentialRepresentation.setValue(password);
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

            List<CredentialRepresentation> list = new ArrayList<>();

            list.add(credentialRepresentation);

            userRepresentation.setCredentials(list);

            userRepresentation.setEmail(email);

            logger.info("Utilisateur trouvé avec sucès {}",login);

            usersResource.get(userRepresentation.getId()).update(userRepresentation);

            logger.info("Utilisateur créer avec sucès {}",username);

        } else {
            throw  new UserNotFoundException("User not found dans keycloak");
        }

    }
}
