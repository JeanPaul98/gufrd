package com.acl.mswauth.controller;

import com.acl.mswauth.dto.MswFormaliteAppliDto;
import com.acl.mswauth.service.MswFormaliteAppliService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formalite")
public class MswFormaliteAppliController {


    private final MswFormaliteAppliService formaliteAppliService;

    public MswFormaliteAppliController(MswFormaliteAppliService formaliteAppliService) {
        this.formaliteAppliService = formaliteAppliService;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PostMapping("application")
    @Operation(summary = "Webservice  pour créer la formalité des applications", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addApplicationPorts(@Valid @RequestBody MswFormaliteAppliDto request) {

        return formaliteAppliService.save(request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PostMapping("update")
    @Operation(summary = "Mise a jour de l'application", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateApplication(@Valid @RequestBody MswFormaliteAppliDto request) {

        return formaliteAppliService.update(request);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Webservice  pour créer la formalité des applications", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> listApplications() {

        return formaliteAppliService.findAllApplications();
    }


}
