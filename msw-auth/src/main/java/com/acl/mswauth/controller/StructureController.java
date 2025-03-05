package com.acl.mswauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acl.mswauth.request.StructureRequest;
import com.acl.mswauth.service.StructureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/structures")
public class StructureController {
	
	private final Logger logger = LoggerFactory.getLogger(StructureController.class);

    @Autowired
    private StructureService structureService;

    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PostMapping("")
   /* @ApiOperation(value = "Webservice creation clients", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> create(@Valid @RequestBody StructureRequest request) {
        logger.info("Creation structure  " + request);
        return structureService.create(request);
    }

    
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PutMapping("/id/{id}")
   /* @ApiOperation(value = "Webservice creation clients", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> update(@PathVariable("id") Long id ,  @Valid @RequestBody StructureRequest request) {
        logger.info("Update structure  " + request);
        return structureService.update(id,request);
    }
    
    
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("/pays/{code}")
    @Operation(summary = "Liste des structures administratives d'un pays à partir du code pays",
            description = "Les structures administratives")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllByPays(@PathVariable("code") String codePays ) {
        return structureService.getAllByPays(codePays);
    } 
    
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("/filles/{structureParenteId}")
    @Operation(summary = "Liste les sous structures  d'une structure administrative à partir de l'id ",
            description = "Les sous structures d'une structure administrative")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllByStructureParente(@PathVariable("structureParenteId") Long id ) {
        return structureService.getAllByStructureParente(id);
    } 
    
    
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("/{id}/assign/{structureParenteId}")
    @Operation(summary  = "API pour l'assignation d'une structure parente à une sous structure ",
            description = "Assignation d'une structure parente à une sous structure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> assignStructureParente(@PathVariable("id") Long id , @PathVariable("structureParenteId") Long structureParenteId) {
        return structureService.assignStructureParente(id,structureParenteId);
    } 

}
