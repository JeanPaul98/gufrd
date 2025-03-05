package com.acl.escalenavire.controller;

import com.acl.escalenavire.dto.BlDto;
import com.acl.escalenavire.models.Bl;
import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.services.LigneMseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ligneMe")
@Tag(name = "LigneMseController")
public class LigneMseController {

    private final LigneMseService ligneMseService;

    public LigneMseController(LigneMseService ligneMseService) {
        this.ligneMseService = ligneMseService;
    }

    @CrossOrigin
    @GetMapping("/numBl")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getlistAnnByNumeroAan(@RequestParam String numBl) {
        return ligneMseService.search(numBl);
    }
}
