package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.ChargeurRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.ChargeurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acl.vbs.fret.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.fret.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/chargeurs", produces = {APPLICATION_JSON_VALUE})
public class ChargeurController {

    private ChargeurService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des chargeurs", OK, service.listeChargeur());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid ChargeurRequest request) {
        return successResponse("Chargeur a été ajouter avec success", CREATED, service.creationChargeur(request));
    }

    @GetMapping("{nom}/prendre")
    public ResponseEntity<HttpResponse> prendre(@PathVariable String nom) {
        return successResponse("Chargeur détail récupérer avec success", OK, service.prendreChargeur(nom));
    }
}
