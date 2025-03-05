package com.acl.escalenavire.controller;


import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.services.BlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bl")
@Tag(name = "BlController")
public class BlController {

    private final BlService blService;

    public BlController(BlService blService) {
        this.blService = blService;
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listProduit(@RequestParam int page,@RequestParam int size) {
        return blService.list(page,size);
    }


    @CrossOrigin
    @GetMapping("/numAtp")
    @ApiOperation(value = "annonce escale", response = ApiResponseModel.class)
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request"),
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getlistAnnByNumeroAan(@RequestParam String numAtp) {
        return blService.searchNumBl(numAtp);
    }
}
