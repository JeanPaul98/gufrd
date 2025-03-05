package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.TransporteurRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.TransporteurService;
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
@RequestMapping(value = API_BASE_URL + "/transporteurs", produces = {APPLICATION_JSON_VALUE})
public class TransporteurController {

    private TransporteurService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des transporteurs", OK, service.listeTransporteur());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid TransporteurRequest request) {
        return successResponse("Transporteur a été ajouter avec success", CREATED, service.creationTransporteur(request));
    }

    @GetMapping("{raisonSociale}/prendre")
    public ResponseEntity<HttpResponse> prendre(@PathVariable String raisonSociale) {
        return successResponse("Transporteur détail récupérer avec success", OK, service.prendreTransporteur(raisonSociale));
    }
}
