package com.acl.mswauth.controller;

import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.request.PortAppliRequest;
import com.acl.mswauth.request.PortClientRequest;
import com.acl.mswauth.dto.PortDto;
import com.acl.mswauth.security.CurrentUser;
import com.acl.mswauth.security.model.KeyCloakUser;
import com.acl.mswauth.service.port.PortClientService;
import com.acl.mswauth.service.port.PortService;
import com.acl.mswauth.service.port.PortUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ports")
public class PortController {

    private final Logger logger = LoggerFactory.getLogger(PortController.class);

    @Autowired
    private PortService portService;

    @Autowired
    private PortClientService portClientService;

    private final PortUserService portUserService;

    public PortController(PortUserService portUserService) {
        this.portUserService = portUserService;
    }

    /**
     * Création d'un port
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("")
    @Operation(summary = "Webservice  pour créer un port", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createPort(@Valid @RequestBody PortDto request) {
        logger.info("Creation application  " + request);
        return portService.create(request);
    }

    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Webservice  pour lister les ports", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllPorts() {
        return portService.getAllPorts();
    }

    /**
     * Ajoute les applications à un port
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("application")
    @Operation(summary = "Webservice  pour ajouter les applications du port", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addApplicationPorts(@Valid @RequestBody PortAppliRequest request) {
        return portService.addApplication(request);
    }

    @CrossOrigin
    @GetMapping("application/{codePort}")
    @Operation(summary = "Webservice  qui liste les applications d'un port", description = "Il faut que le port existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getApplicationPorts(@PathVariable(name = "codePort") String port) {
        return portService.getAllApplicationByPort(port);
    }

    /**
     * Ajout d'un client à un port
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("client")
    @Operation(summary = "Webservice  qui ajoute un client à plusieurs port", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addApplicationClient(@Valid @RequestBody PortClientRequest request) {
        return portService.addClient(request);
    }

    /**
     * Ajout d'un client à un port
     * @param
     * @return
     */
    @CrossOrigin
    @PostMapping("all/client")
    @Operation(summary = "Webservice  qui ajoute un client à plusieurs port", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addMultiPortClient(@RequestParam("compteClient") String compteClient,
                                                @RequestParam("ports") String ports) {
        return portClientService.addPortClients(compteClient, ports);
    }


    @CrossOrigin
    @GetMapping("client/compte")
    @Operation(summary = "Webservice  qui liste les ports  d'un clients", description = "Il faut que le port existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getPortClientKeycloak() {

            return portUserService.getPortUserClient();



    }


    @CrossOrigin
    @GetMapping("pays/{codePays}")
    @Operation(summary = "Webservice  qui liste les ports  d'un pays", description = "Il faut que le port existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getPortPays(@PathVariable(name = "codePays") String codePays) {
        logger.info("getPortClient compte client {} ", codePays);
        return portService.getPortPays(codePays);
    }





}
