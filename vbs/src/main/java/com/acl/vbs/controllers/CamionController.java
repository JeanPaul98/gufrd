/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.controllers;

import com.acl.vbs.requests.CamionRequest;
import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.CamionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/camions", produces = {APPLICATION_JSON_VALUE})
public class CamionController {

    private CamionService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des camions", OK, service.listeCamion());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid CamionRequest request) {
        return successResponse("Camion a été ajouter avec success", CREATED, service.creationCamion(request));
    }

    @GetMapping("{immatriculation}/prendre")
    public ResponseEntity<HttpResponse> prendre(@PathVariable String immatriculation) {
        return successResponse("Camion détail récupérer avec success", OK, service.prendreCamion(immatriculation));
    }

}
