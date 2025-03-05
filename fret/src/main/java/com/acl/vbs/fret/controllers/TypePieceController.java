package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.TypePieceRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.TypePieceService;
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
@RequestMapping(value = API_BASE_URL + "/type-piece", produces = {APPLICATION_JSON_VALUE})
public class TypePieceController {

    TypePieceService typePieceService;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des Types de !", OK, typePieceService.list());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid TypePieceRequest request) {
        return successResponse("Création du type de pièce réussie!", CREATED, typePieceService.create(request));
    }

    @DeleteMapping(value = "{id}/delete")
    public ResponseEntity<HttpResponse> delete(@PathVariable Long id) {
        return successResponse("Suppression du type de pièce réussie!", CREATED, typePieceService.deleteTypePiece(id));
    }
}
