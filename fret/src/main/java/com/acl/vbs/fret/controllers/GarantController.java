package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.requests.GarantRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.GarantService;
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
@RequestMapping(value = API_BASE_URL + "/garant", produces = {APPLICATION_JSON_VALUE})
public class GarantController {

    private final GarantService garantService;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des garant", OK, garantService.list());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid GarantRequest request) {
        return successResponse("garant a été ajouter avec success", CREATED, garantService.create(request));
    }
}
