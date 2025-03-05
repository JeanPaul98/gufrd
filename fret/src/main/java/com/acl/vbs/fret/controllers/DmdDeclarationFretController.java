package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.DmdDeclarationFretRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.DmdDeclarationFretService;
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
@RequestMapping(value = API_BASE_URL + "/dmdDeclfret", produces = {APPLICATION_JSON_VALUE})
public class DmdDeclarationFretController {

    private final DmdDeclarationFretService dmdDeclarationFretService;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("La liste de demandes de déclartation du fret", OK, dmdDeclarationFretService.getAllByDeclarant());
    }

    @GetMapping("chargeur/liste")
    public ResponseEntity<HttpResponse> chargeurListe() {
        return successResponse("La liste de demandes de déclartation du fret", OK, dmdDeclarationFretService.getAllByChargeur());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid DmdDeclarationFretRequest request) {
        return successResponse("Enregistrement de la demande de déclaration du fret réussi...", CREATED, dmdDeclarationFretService.create(request));
    }

    @GetMapping("{idDeclarationFret}/prendre")
    public ResponseEntity<HttpResponse> prendre(@PathVariable Long idDeclarationFret) {
        return successResponse("Détails de la demande de déclartation du fret", OK, dmdDeclarationFretService.getById(idDeclarationFret));
    }

}
