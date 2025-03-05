package com.acl.mswauth.controller;

import com.acl.mswauth.record.UserRegistrationRecord;
import com.acl.mswauth.service.KeycloakUserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.ClientRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keycloak")
@Slf4j
public class KeycloakController {

    private final KeycloakUserService keycloakUserService;



    public KeycloakController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping("")
    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {
        System.out.println(userRegistrationRecord.username());
        return keycloakUserService.createUser(userRegistrationRecord);
    }

    @GetMapping("clients")
    @CrossOrigin
    public List<ClientRepresentation> listClients() {
        return keycloakUserService.getAllClient();
    }




}
