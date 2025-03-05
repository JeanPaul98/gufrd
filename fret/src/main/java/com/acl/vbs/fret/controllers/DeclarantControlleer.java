package com.acl.vbs.fret.controllers;


import com.acl.vbs.fret.requests.DeclarantRequest;
import com.acl.vbs.fret.responses.HttpResponse;
import com.acl.vbs.fret.services.DeclarantSevice;
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
@RequestMapping(value = API_BASE_URL + "/declarant", produces = {APPLICATION_JSON_VALUE})
public class DeclarantControlleer {

    private final DeclarantSevice declarantSevice;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> liste() {
        return successResponse("Liste des declarant", OK, declarantSevice.list());
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid DeclarantRequest request) {
        return successResponse("declarant a été ajouter avec success", CREATED, declarantSevice.create(request));
    }
}
