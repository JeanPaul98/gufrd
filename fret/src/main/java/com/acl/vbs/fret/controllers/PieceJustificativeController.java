package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.PieceJustificativeRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.PieceJustificativeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acl.vbs.fret.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.fret.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/piece", produces = {APPLICATION_JSON_VALUE})
public class PieceJustificativeController {

    PieceJustificativeService pieceJustificativeService;

    @GetMapping("{idDeclarationFret}/piece")
    public ResponseEntity<HttpResponse> pieceJustificativeFret(@PathVariable Long idDeclarationFret) {
        return successResponse("Liste des Pièces justificatives du fret de l'id " + idDeclarationFret + ".", OK, pieceJustificativeService.listPieceJustificative(idDeclarationFret));
    }

    @PostMapping(value = "creation", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpResponse> create(@ModelAttribute @Valid PieceJustificativeRequest request) {
        return successResponse("Pièces justificatives ajoutées avec succès!", CREATED, pieceJustificativeService.create(request));
    }

}
