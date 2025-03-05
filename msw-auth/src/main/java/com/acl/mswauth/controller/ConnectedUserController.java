package com.acl.mswauth.controller;

import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.service.ConnectedUserService;
import com.acl.mswauth.servicesImpl.MswConnectedUserServiceImp;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 27/09/2024
 * @project msw-auth
 */
@RestController
@RequestMapping("/api/connected")
public class ConnectedUserController {

private final ConnectedUserService connectedUserService;

    private final Logger logger = LoggerFactory.getLogger(ConnectedUserController.class);


    public ConnectedUserController(ConnectedUserService connectedUserService) {
        this.connectedUserService = connectedUserService;
    }

    @CrossOrigin
    @GetMapping("session/{session}")
    public ResponseEntity<?> getConnectedUsers(@PathVariable("session") String session) {
        return connectedUserService.findBySession(session);

    }

}
