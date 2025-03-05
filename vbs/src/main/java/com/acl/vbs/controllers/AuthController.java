package com.acl.vbs.controllers;

import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("auth")
    public ResponseEntity<HttpResponse> getAuthInfo() {
        return successResponse("User info", OK, authenticationService.getAuthInfo());
    }
}
