package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.fret.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.fret.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/paiements", produces = {APPLICATION_JSON_VALUE})
public class PaiementController {

    private final PaiementService service;

    @PostMapping(value = "declaration/{declarationId}")
    public ResponseEntity<HttpResponse> pay(@PathVariable Long declarationId) {
        return successResponse("L'url paiements effectués", OK, service.payementDeclaration(declarationId));
    }
}
