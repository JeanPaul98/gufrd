package com.acl.formaliteagricultureapi.controller.imports.autorisation;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.consommation.AutorisationConsommationProduitClientDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.consommation.AutorisationConsommationProduitClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.autorisation.demande.consommation.AutorisationConsomationProduitClientManyService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.consommation.AutorisationConsommationLocalClientCRUDService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.consommation.AutorisationConsommationUpService;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 21/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/autorisation/consommation")
@Tag(name = "AutorisationConsommationProduitControlleur",
        description = "Processus de formalité \n" +
                "Autorisation d'importation de produit pour consommation \n")
public class AutorisationConsommationProduitControlleur {

    Logger logger = LoggerFactory.getLogger(AutorisationConsommationProduitControlleur.class);

    private final AutorisationConsommationLocalClientCRUDService autorisationConsommationLocalClientCRUDService;
    private final AutorisationConsomationProduitClientManyService autorisationConsomationProduitClientManyService;

    private final AutorisationConsommationUpService autorisationConsommationUpService;

    private final FormaliteService formaliteService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public AutorisationConsommationProduitControlleur(AutorisationConsommationLocalClientCRUDService
                                                              autorisationConsommationLocalClientCRUDService,
                                                      AutorisationConsomationProduitClientManyService
                                                              autorisationConsomationProduitClientManyService, AutorisationConsommationUpService autorisationConsommationUpService,
                                                      FormaliteService formaliteService) {
        this.autorisationConsommationLocalClientCRUDService = autorisationConsommationLocalClientCRUDService;
        this.autorisationConsomationProduitClientManyService = autorisationConsomationProduitClientManyService;
        this.autorisationConsommationUpService = autorisationConsommationUpService;
        this.formaliteService=formaliteService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'autorisation de consommation local")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationEnlevement(
            @Valid @RequestBody AutorisationImportationDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return autorisationConsommationLocalClientCRUDService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return autorisationConsomationProduitClientManyService.listAutorisationEnlevement(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.consommation"));

    }


    @CrossOrigin
    @GetMapping("nonsoumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumisClient(@RequestParam("compteClient") String compteClient) {

        return autorisationConsomationProduitClientManyService.listAutorisationConsomationByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.consommation"),compteClient);

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return autorisationConsomationProduitClientManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.consommation"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisClient(@RequestParam("compteClient") String compteClient) {

        return autorisationConsomationProduitClientManyService.listAutorisationConsomationByCompte(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.consommation"),compteClient);

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour l'autorisation d'importation de produit à consommation local rejetée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return autorisationConsomationProduitClientManyService.listAutorisationEnlevement(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.consommation"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation d'importation de produit à consommation local Accepter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return autorisationConsomationProduitClientManyService.listAutorisationEnlevement(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.consommation"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return autorisationConsomationProduitClientManyService.listAutorisationEnlevement(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.consommation"));

    }

    @CrossOrigin
    @PostMapping("updateDemande")
    @Operation(summary = "Soumission de sa demande de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> soumettreDemandeAutorisationEnlevement(
            @Valid @RequestBody AutorisationConsommationProduitClientListDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return autorisationConsommationUpService.updateDemande(request);

    }

    @CrossOrigin
    @PostMapping("accepterdemande")
    @Operation(summary = "Accepter la liste des demandes soumises de l'autorisation de l'envelement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> accepterDemande(@Valid @RequestBody UpdateFormaliteDto request,
                                             HttpServletRequest httprequest, BindingResult result)  {

        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.accepterDemandeAutorisation(request);


    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return autorisationConsomationProduitClientManyService.listStatDemande();

    }


}
