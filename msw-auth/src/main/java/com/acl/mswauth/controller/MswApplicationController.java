package com.acl.mswauth.controller;


import com.acl.mswauth.dto.user.UserApplisGroupeDto;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.dto.ApplicationDto;
import com.acl.mswauth.request.UserApplicationRequest;
import com.acl.mswauth.security.CurrentUser;
import com.acl.mswauth.security.impl.UserPrincipal;
import com.acl.mswauth.service.application.ApplicationClientService;
import com.acl.mswauth.service.application.MswApplicationGroupeService;
import com.acl.mswauth.service.application.MswApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/applications")
public class MswApplicationController {

    private final Logger logger = LoggerFactory.getLogger(MswApplicationController.class);

    private final MswApplicationGroupeService mswApplicationGroupeService;

    private final ApplicationClientService applicationClientService;

    @Autowired
    private MswApplicationService mswApplicationService;

    public MswApplicationController(MswApplicationGroupeService mswApplicationGroupeService, ApplicationClientService applicationClientService) {
        this.mswApplicationGroupeService = mswApplicationGroupeService;
        this.applicationClientService = applicationClientService;
    }

    @CrossOrigin
    @PostMapping("")
    @Operation(summary = "Webservice qui crée une application", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
   public ResponseEntity<?> createApplication(@Valid @RequestBody ApplicationDto request) {
        logger.info("Creation application  " + request);
        return mswApplicationService.create(request);
    }

    @CrossOrigin
    @PostMapping("update")
    @Operation(summary = "Webservice qui fait la mise a jour de l'application", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateApplication(@Valid @RequestBody ApplicationDto request) {
        logger.info("Update application, {}  ", request);
        return mswApplicationService.update(request);
    }

    @CrossOrigin
    @GetMapping("")
  /*  @ApiOperation(value = "Webservice liste des clients", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> getAllApplications() {
        return mswApplicationService.getAllApplications();
    }

    /**
     * Crée les applications d'un groupe
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("groupe")
    @Operation(summary = "Webservice  pour ajouter une application à un groupe", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> addApplicationGroupe(@Valid @RequestBody UserApplicationRequest request) {

        return mswApplicationService.addApplicationGroupePort(request);
    }

    /**
     * Crée les applications d'un groupe
     * @param groupId
     * @return
     */
    @CrossOrigin
    @GetMapping("groupe")
    @Operation(summary = "Webservice  pour ajouter une application à un groupe", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllApplicationByGroupe(@RequestParam Long groupId) {

        return mswApplicationGroupeService.getAllApplicationByGroupe(groupId);
    }



    /**
     * Enlever une application à un groupe
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("groupe/remove")
    @Operation(summary = "Webservice  suprimer une application à un groupe", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> removeApplicationGroupe(@Valid @RequestBody UserApplisGroupeDto request) {

        return mswApplicationGroupeService.removeApplicationGroupe(request);
    }


    @CrossOrigin
    @GetMapping("/groupe/id/{id}")
  /*  @ApiOperation(value = "Webservice liste des clients", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> getAllApplicationsByGroupeId(@PathVariable  Long id) {
        return mswApplicationService.getAllApplicationsByGroupeId(id);
    }

    @CrossOrigin
    @GetMapping("/groupe/nom/{nom}")
  /*@ApiOperation(value = "Webservice liste des clients", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> getAllApplicationsByGroupeName(@PathVariable  String nom) {
        return mswApplicationService.getAllApplicationsByGroupeName(nom);
    }

    /**
     * Liste les applications d'un client
     * @param currentUser
     * @return
     */
    @CrossOrigin
    @GetMapping("client")
    @Operation(summary = "Webservice  qui liste les applications d'un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllApplicationsByUser( @CurrentUser UserPrincipal currentUser) {

        logger.info("utilisateur connecté  {}", currentUser.getLogin());
        return mswApplicationService.getAllAplicationByClient(currentUser);
    }


    /**
     * Liste les applications d'un client
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("currentuser")
    @Operation(summary = "Webservice  qui liste les applications d'un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return mswApplicationService.getAllAplicationByKeycloakUser(currentUserName);

        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("clients")
    @Operation(summary = "Webservice  qui liste les applications d'un client",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getCurrentClientApplication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return applicationClientService.getApplicationClients(currentUserName);

        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("codeClient")
    @Operation(summary = "Webservice  qui liste les applications d'un client grace à son code",
            description = "code client obligatoire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getApplicationByCompteClient(@RequestParam String codeClient) {

        return  applicationClientService.getApplicationByCompteClient(codeClient);

    }





    /**
     * Détail les infos d'une application et son url
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("{id}")
  /*@ApiOperation(value = "Webservice liste les applications d'un client", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
   */ public ResponseEntity<?> getApplicationDetailsUrl(@PathVariable Long id) {
        return mswApplicationService.getApplicationDetails(id);
    }





}
