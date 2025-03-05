package com.acl.formaliteagricultureapi.controller.imports.autorisation;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux.AutorisationEnlevementClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux.AutorisationAlimentAnimauxClientCDService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux.AutorisationEnlevementClientManyService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux.AutorisationEnlevementClientUpService;
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
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/autorisation/alimentAnimaux")
@Tag(name = "AutorisationAlimentAnimauxClientControlleur",
        description = "Processus de formalité d'autorisation d'enlèvement")
public class AutorisationAlimentAnimauxClientControlleur {

    Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientControlleur.class);

    private final AutorisationAlimentAnimauxClientCDService autorisationAlimentAnimauxClientCDService;

    private final AutorisationEnlevementClientManyService autorisationEnlevementClientManyService;

    private final FormaliteService formaliteService;

    private final AutorisationEnlevementClientUpService autorisationEnlevementClientUpService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public AutorisationAlimentAnimauxClientControlleur(AutorisationAlimentAnimauxClientCDService autorisationAlimentAnimauxClientCDService,
                                                       AutorisationEnlevementClientManyService
                                                           autorisationEnlevementClientManyService,
                                                       FormaliteService formaliteService,
                                                       AutorisationEnlevementClientUpService
                                                           autorisationEnlevementClientUpService) {
        this.autorisationAlimentAnimauxClientCDService = autorisationAlimentAnimauxClientCDService;
        this.autorisationEnlevementClientManyService = autorisationEnlevementClientManyService;
        this.formaliteService=formaliteService;
        this.autorisationEnlevementClientUpService=autorisationEnlevementClientUpService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'autorisation d'importation d'alliment pour animaux")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisation(
            @Valid @RequestBody AutorisationImportationDto request,
            HttpServletRequest httprequest, BindingResult result) {

        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return autorisationAlimentAnimauxClientCDService.create(request);
    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return autorisationEnlevementClientManyService.listAutorisationEnlevement(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"));

    }

    @CrossOrigin
    @GetMapping("nonsoumis/compteClient")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumiseClient(@RequestParam("compteClient") String compteClient) {
        logger.info("Liste non soumise envoie ");
        return autorisationEnlevementClientManyService.listAutorisationEnlevementByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"),compteClient);

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return autorisationEnlevementClientManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisClient(@RequestParam("compteClient") String compteClient) {

        return autorisationEnlevementClientManyService.listAutorisationEnlevementByCompte(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"),compteClient);

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return autorisationEnlevementClientManyService.listAutorisationEnlevement(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return autorisationEnlevementClientManyService.listAutorisationEnlevement(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return autorisationEnlevementClientManyService.listAutorisationEnlevement(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.alimentAnimaux"));

    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return autorisationEnlevementClientManyService.listStatDemande();

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
    @PostMapping("updateDemande")
    @Operation(summary = "Mise à jour de  la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> updateDemande(
            @Valid @RequestBody AutorisationImportationUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("cancelDemande json "+request);
        return autorisationEnlevementClientUpService.updateDemande(request);

    }
}
