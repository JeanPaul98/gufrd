package com.acl.mswauth.service;

import com.acl.mswauth.record.UserRegistrationRecord;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KeycloakUserService {
    UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId);
    UsersResource getUsersResource();

    List<ClientRepresentation> getAllClient();

    UserRegistrationRecord createStructure(UserRegistrationRecord userRegistrationRecord);
}
