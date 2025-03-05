package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.ParametrageRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.ParametrageService;
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
@RequestMapping(value = API_BASE_URL + "/parametrages", produces = {APPLICATION_JSON_VALUE})
public class ParametrageController {

    private ParametrageService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des parametrages", OK, service.listeParametrage());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid ParametrageRequest request) {
        return successResponse("parametrages a été ajouter avec success", CREATED, service.creationParametrage(request));
    }

    @GetMapping("{id}/prendre")
    public ResponseEntity<HttpResponse> prendre(@PathVariable Long id) {
        return successResponse("parametrages détail récupérer avec success", OK, service.prendreParametrage(id));
    }

}
