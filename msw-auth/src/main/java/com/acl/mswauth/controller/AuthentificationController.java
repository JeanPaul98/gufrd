package com.acl.mswauth.controller;


import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.request.LoginRequest;
import com.acl.mswauth.request.RegisterRequest;
import com.acl.mswauth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/auth", produces = {APPLICATION_JSON_VALUE})
public class AuthentificationController {

    private final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);

private  final AuthService authService;

    public AuthentificationController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin
    @PostMapping("login")
 /*   @ApiOperation(value = "Webservice d'authentification web", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
    */public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("authentification loginRequest " + loginRequest);
        return new ResponseEntity<>(HttpStatus.OK.name(), HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("currentuser")
    public ResponseEntity<?> curentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String currentUserName = authentication.getName();
            logger.info("authentification loginRequest {} ", currentUserName);
            return  authService.currentUser(currentUserName);

        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);

    }






}
