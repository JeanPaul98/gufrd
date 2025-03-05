package com.acl.formaliteagricultureapi.controller.general;


import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import com.acl.formaliteagricultureapi.services.general.SocieteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/societe")
@Tag(name = "SocieteServiceController")
public class SocieteController {

    private final SocieteService societeService;

    public SocieteController(SocieteService societeService) {
        this.societeService = societeService;
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSociete() {
        return societeService.listSociete();
    }


    @CrossOrigin
    @PostMapping("create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> addSociete(@RequestBody SocieteDto societeDto) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return societeService.addSociete(societeDto);
    }


    @CrossOrigin
    @PutMapping("update/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> updateSociete(@PathVariable long id,@RequestBody SocieteDto societeDto) {
        return societeService.updateSociete(societeDto,id);
    }
}
