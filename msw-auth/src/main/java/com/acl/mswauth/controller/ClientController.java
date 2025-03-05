package com.acl.mswauth.controller;


import com.acl.mswauth.dto.ClientDto;
import com.acl.mswauth.dto.ClientPortGroupeDto;
import com.acl.mswauth.dto.PortClientDto;
import com.acl.mswauth.service.client.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientManyService manyService;

    private final ClientUpManyService clientUpManyService;

    private final ClientCurrentUserService clientCurrentUserService;

    private final ClientSearchService clientSearchService;


    @Autowired
    private ClientService clientService;

    public ClientController(ClientManyService manyService, ClientUpManyService clientUpManyService,
                            ClientCurrentUserService clientCurrentUserService, ClientSearchService clientSearchService) {
        this.manyService = manyService;
        this.clientUpManyService = clientUpManyService;
        this.clientCurrentUserService = clientCurrentUserService;
        this.clientSearchService = clientSearchService;
    }

    @CrossOrigin
    @PostMapping("")
    @Operation(summary = "Webservice  qui crée le client", description = "création de client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDto clientDto) {
        logger.info("Creation client {}  ", clientDto);
        return clientService.create(clientDto);
    }

    @CrossOrigin
    @GetMapping("currentuser")
    @Operation(summary = "Webservice  qui liste les clients du port", description = "Webservice qui liste" +
            "les clients de l'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUserClient() {
        return clientCurrentUserService.getAllClientByUser();
    }

    @CrossOrigin
    @PostMapping("update")
    @Operation(summary = "Webservice  qui crée le client", description = "création de client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDto clientDto) {
        logger.info("Creation client {}  ", clientDto);
        return clientService.update(clientDto);
    }

    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Webservice  qui liste des clients", description = "Liste les clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getClient() {
        return clientService.getAllClients();
    }


    @CrossOrigin
    @GetMapping("users/{codeClient}")
    @Operation(summary = "Webservice  qui liste les utilisateurs d'un client",
            description = "Les utilisateurs d'un clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUserClient(@PathVariable("codeClient") String codeClient) {
        return clientService.getAllUserByClient(codeClient);
    }

    @CrossOrigin
    @GetMapping("search")
    @Operation(summary = "Web service pour rechercher un client",
            description = "Les clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> searchUsersKeyword(@RequestParam String keyword, int page, int size) {
        return clientSearchService.getSearchResult(keyword, page,size);
    }


    @CrossOrigin
    @GetMapping("users")
    @Operation(summary = "Webservice  qui liste les utilisateurs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUsers() {
        return clientService.getAllUsers();
    }


    @CrossOrigin
    @GetMapping("alls")
    @Operation(summary = "Webservice  qui liste les utilisateurs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllPaginate(@RequestParam("page")int page, @RequestParam("size")int size) {

        return clientService.getAllPaginateClient(page,size);
    }


    @CrossOrigin
    @GetMapping("{login}")
    @Operation(summary = "Webservice  qui recherche un utilisateur",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> findClient(@PathVariable("login") String login) {
        return clientService.getClient(login);
    }


    @CrossOrigin
    @GetMapping("ports")
    @Operation(summary = "Webservice  qui liste les ports du clients",
            description = "Le port des clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> findByPortsClient(@RequestParam("compteClient") String compteClient) {
        return manyService.getPortByClient(compteClient);
    }


    @CrossOrigin
    @PostMapping("portclient")
    @Operation(summary = "Webservice qui ajoute un client au port et à son groupe", description = "création de client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addClientToPort(@Valid @RequestBody ClientPortGroupeDto request) {

         logger.info("Creation client {}  ", request);
        return clientUpManyService.addClientGroupePort(request);
    }

}
