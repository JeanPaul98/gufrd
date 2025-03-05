package com.acl.mswauth.servicesImpl;

import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.service.KeycloakUserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class KeycloakUserServiceImp implements KeycloakUserService {

    @Value("${keycloak.admin.realm}")
    private String realm;
    private Keycloak keycloak;

    private final Logger logger = LoggerFactory.getLogger(KeycloakUserServiceImp.class);


    public KeycloakUserServiceImp(Keycloak keycloak) {
        this.keycloak = keycloak;
    }


    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user=new UserRepresentation();

        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);
        user.setEnabled(true);

        //Mapping client

        logger.info("Creating user {}", userRegistrationRecord.username());
        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        List<CredentialRepresentation> list = new ArrayList<>();

        list.add(credentialRepresentation);

        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        try (Response response = usersResource.create(user)) {

            logger.info("Create response : {}", response.readEntity(String.class));
            if (Objects.equals(201, response.getStatus())) {


                List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
                if (!CollectionUtils.isEmpty(representationList)) {
                    UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                    assert userRepresentation1 != null;
                    //   emailVerification(userRepresentation1.getId());
                }
                return userRegistrationRecord;
            }
        }

//        response.readEntity()

        return null;
    }

    @Override
    public UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRegistrationRecord createStructure(UserRegistrationRecord userRegistrationRecord) {
        UserRepresentation user=new UserRepresentation();

        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);
        user.setEnabled(true);

        //Mapping client

        logger.info("Creating user {}", userRegistrationRecord.username());
        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        List<CredentialRepresentation> list = new ArrayList<>();

        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        try (Response response = usersResource.create(user)) {

            logger.info("Create response : {}", response.readEntity(String.class));


            RoleRepresentation clientRole = getAllRoles("d3b2e2f5-3606-4148-ad23-4007cbe6fe80");



            if (Objects.equals(201, response.getStatus())) {

                List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
                keycloak.realm(realm)
                        .users()
                        .get(representationList.get(0).getId())
                        .roles()
                        .clientLevel("d3b2e2f5-3606-4148-ad23-4007cbe6fe80")
                        .add(Collections.singletonList(clientRole));
                logger.info("Role mise a jou avec succès");

                if (!CollectionUtils.isEmpty(representationList)) {
                    UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                    assert userRepresentation1 != null;
                    //   emailVerification(userRepresentation1.getId());
                }
                return userRegistrationRecord;
            }
        }

//        response.readEntity()

        return null;
    }


    @Override
    public List<ClientRepresentation> getAllClient() {
        List<ClientRepresentation> clients = keycloak.realm(realm)
                .clients()
                .findAll();

        List<ClientRepresentation>  clientRepresentations = keycloak.realm(realm).clients().findByClientId("GUFORD-USER-INTERFACE");

        clientRepresentations.get(0).getClientId() ;
        logger.info("getAllClient {}", clientRepresentations.get(0).getId());
        return clients;
    }


    public RoleRepresentation getAllRoles(String uuid) {

        RoleRepresentation clientRole = keycloak.realm(realm)
                .clients()
                .get(uuid)
                .roles()
                .get("CONSULTANT") // Nom du rôle
                .toRepresentation();

        return clientRole;
    }

    public String getUseuiid(String login) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> user = usersResource.search(login, true);
    return user.get(0).getId();
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        return null;
    }
}
