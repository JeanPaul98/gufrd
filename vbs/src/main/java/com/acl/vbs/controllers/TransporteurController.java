package com.acl.vbs.controllers;

import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.TransporteurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/transporteurs", produces = {APPLICATION_JSON_VALUE})
public class TransporteurController {

    private TransporteurService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des transporteurs", OK, service.listTransporteur());
    }
}
