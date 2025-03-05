package com.acl.vbs.controllers;


import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.LigneMseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/search", produces = {APPLICATION_JSON_VALUE})
public class LigneMseController {

    private final LigneMseService ligneMseService;

    @GetMapping("numBl")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<HttpResponse> searchByNumBl(@RequestParam String numBl) {
        return successResponse("Information des marchandise transporter", OK, ligneMseService.filterNumBl(numBl));
    }

    @GetMapping("numConteneur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<HttpResponse> searchBynumConteneur(@RequestParam String numConteneur) {
        return successResponse("Information des marchandise transporter", OK, ligneMseService.filterNumConteneur(numConteneur));
    }

//    @GetMapping("numChassis")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
//            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
//            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
//    public ResponseEntity<HttpResponse> searchBynumChassi(@RequestParam String numChassis) {
//        return successResponse("Information des marchandise transporter", OK, ligneMseService.filterNumChassi(numChassis));
//    }
}
