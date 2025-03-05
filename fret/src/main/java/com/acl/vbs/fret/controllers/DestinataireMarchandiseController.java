package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.DestinataireMarchandiseRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.DestinataireMarchandiseService;
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
@RequestMapping(value = API_BASE_URL + "/destination_marchandise", produces = {APPLICATION_JSON_VALUE})
public class DestinataireMarchandiseController {

    private final DestinataireMarchandiseService destinataireMarchandiseService;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des destination marchandise", OK, destinataireMarchandiseService.getAll());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid DestinataireMarchandiseRequest request) {
        return successResponse("destination marchandise a été ajouter avec success", CREATED, destinataireMarchandiseService.create(request));
    }
}
