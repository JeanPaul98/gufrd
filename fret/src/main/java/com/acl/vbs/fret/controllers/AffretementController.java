package com.acl.vbs.fret.controllers;

import com.acl.vbs.fret.responses.AffretementResponse;
import com.acl.vbs.fret.requests.AffretementRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.AffretementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acl.vbs.fret.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.fret.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = API_BASE_URL+"/affretement", produces = {APPLICATION_JSON_VALUE})
public class AffretementController {

    private final AffretementService service;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("La liste des affretements réalisés", OK, service.getAll());
    }

    @PostMapping(value="creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid AffretementRequest request){
        return successResponse("Affretement Enregistré!", CREATED, service.create(request));
    }
}
