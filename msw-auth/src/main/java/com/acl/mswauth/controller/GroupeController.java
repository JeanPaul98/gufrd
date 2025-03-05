package com.acl.mswauth.controller;


import com.acl.mswauth.dto.ClientGroupeDto;
import com.acl.mswauth.dto.GroupeDtoUpdate;
import com.acl.mswauth.request.GroupeClientRequest;
import com.acl.mswauth.dto.GroupeDto;
import com.acl.mswauth.service.groupe.GroupeManyService;
import com.acl.mswauth.service.groupe.GroupeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groupes")
@Tag(name = "GroupeController",
        description = "Processus de création de groupe")
public class GroupeController {

    private final Logger logger = LoggerFactory.getLogger(GroupeController.class);

    @Autowired
    private GroupeService groupeService;

    private final GroupeManyService groupeManyService;

    public GroupeController(GroupeManyService groupeManyService) {
        this.groupeManyService = groupeManyService;
    }

    @CrossOrigin
    @PostMapping("")
    @Operation(summary = "Création de groupes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createGroupe(@Valid @RequestBody GroupeDto groupeDto) {
        logger.info("Creation groupes " + groupeDto);
        return groupeService.create(groupeDto);
    }

    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Liste des groupes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getGroupes() {
        return groupeManyService.getAllGroupes();
    }

    @CrossOrigin
    @PostMapping("update")
    @Operation(summary = "Création de groupes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeGroupe(@Valid @RequestBody GroupeDtoUpdate request) {
        logger.info("Creation groupes " + request);
        return groupeService.update(request);
    }


    /**
     * Création d'un groupe de client
     * @param groupeClientRequest
     * @return
     */
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PostMapping("client")
    @Operation(summary = "Ajout d'un groupe à un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addClientGroupe(@Valid @RequestBody GroupeClientRequest groupeClientRequest) {
        return groupeService.addClientGroupe(groupeClientRequest);
    }

    /**
     * Groupe des utilisateurs
     * @return
     */
    @CrossOrigin
    @PostMapping("all/client")
    @Operation(summary = "Ajout multiple  d'un groupe à un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addMultiClientGroupe(@RequestBody ClientGroupeDto request) {
        return groupeService.addGroupesClient(request);
    }


    /**
     *Liste les applications d'un groupe
     * @param compteclient
     * @return
     */
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("client/compteclient/{compteclient}")
    @Operation(summary = "Web  qui liste les groupes d'un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getGroupesByCompteClient(@PathVariable String compteclient ) {
        return groupeService.getAllGroupesByClientCompte(compteclient);
    }

    /**
     * retrieve Groupe clients
     * @param id
     * @return
     */
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("client/id/{id}")
  /*  @ApiOperation(value = "Webservice liste des groupes", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> getGroupesByClient(@PathVariable  Long id ) {
    	logger.info("ClientId : ", id);
        return groupeService.getAllGroupesByClientId(id);
    }



}
