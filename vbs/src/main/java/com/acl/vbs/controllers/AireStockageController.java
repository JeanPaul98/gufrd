package com.acl.vbs.controllers;


import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.AireStockageService;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/air-stockage", produces = {APPLICATION_JSON_VALUE})
public class AireStockageController {

    private final AireStockageService aireStockageService;

    @GetMapping("list")
    public ResponseEntity<HttpResponse> list() {
        return successResponse("Liste des air stockage", OK, aireStockageService.list());
    }
}
