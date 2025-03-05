package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.fret.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.fret.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("auth")
    public ResponseEntity<HttpResponse> authInfo() {
        return successResponse("User info", OK, authenticationService.getAuthInfo());
    }

    @PostMapping("auth/chargeur")
    public ResponseEntity<HttpResponse> chargeurInfo() {
        return successResponse("User info", OK, authenticationService.getAuthChargeurInfo());
    }
}
