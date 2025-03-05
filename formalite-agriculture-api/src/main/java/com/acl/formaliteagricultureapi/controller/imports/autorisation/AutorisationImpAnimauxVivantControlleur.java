package com.acl.formaliteagricultureapi.controller.imports.autorisation;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantsClientDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantClientCRUDService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantClientManyService;
import com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant.AutorisationAnimauxVivantUpService;
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
@RequestMapping("api/v1/autorisation/animauxVivant")
@Tag(name = "AutorisationImpAnimauxVivantControlleur",
        description = "Processus de formalité d'importation d'annimaux vivant")
public class AutorisationImpAnimauxVivantControlleur {

    private final AutorisationAnimauxVivantClientCRUDService autorisationAnimauxVivantClientCRUDService;
    Logger logger = LoggerFactory.getLogger(AutorisationImpAnimauxVivantControlleur.class);

    private final AutorisationAnimauxVivantClientManyService autorisationAnimauxVivantClientManyService;

    private final FormaliteService formaliteService;

    private final AutorisationAnimauxVivantUpService autorisationAnimauxVivantUpService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public AutorisationImpAnimauxVivantControlleur(AutorisationAnimauxVivantClientCRUDService
                                                           autorisationAnimauxVivantClientCRUDService,
                                                   AutorisationAnimauxVivantClientManyService
                                                           autorisationAnimauxVivantClientManyService,
                                                   FormaliteService formaliteService, AutorisationAnimauxVivantUpService autorisationAnimauxVivantUpService) {
        this.autorisationAnimauxVivantClientCRUDService = autorisationAnimauxVivantClientCRUDService;
        this.autorisationAnimauxVivantClientManyService = autorisationAnimauxVivantClientManyService;
        this.formaliteService=formaliteService;
        this.autorisationAnimauxVivantUpService = autorisationAnimauxVivantUpService;
    }


    @CrossOrigin
    @PostMapping("/insert")
    @Operation(summary = "Insérer les données pour l'autorisation importation animaux vivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationAnimaux(
            @Valid @RequestBody AutorisationImportationDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Soumission Demane , {}", request);
        return autorisationAnimauxVivantClientCRUDService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation animauxVivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimaux(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.animauxVivant"));

    }

    @CrossOrigin
    @GetMapping("nonsoumis/client")
    @Operation(summary = "Liste les données pour l'autorisation animauxVivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumiseClient(@RequestParam("compteClient") String compteClient) {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimauxByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.animauxVivant"), compteClient);

    }

    @CrossOrigin
    @GetMapping("soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimaux(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.animauxVivant"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisClient(@RequestParam("compteClient") String compteClient) {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimauxByCompte(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.animauxVivant"), compteClient);

    }


    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation d'animaux vivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimaux(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.animauxVivant"));

    }


    @CrossOrigin
    @GetMapping("rejetee/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation d'animaux vivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeterClient(@RequestParam String compteClient) {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimauxByCompte(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.animauxVivant"), compteClient);

    }
    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimaux(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.animauxVivant"));

    }

    @CrossOrigin
    @GetMapping("accepter/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepterClient(@RequestParam String compteClient) {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimauxByCompte(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.animauxVivant"), compteClient);

    }

    @CrossOrigin
    @GetMapping("traiter/client")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter(@RequestParam String compteClient) {

        return autorisationAnimauxVivantClientManyService.listAutorisationAnimauxByCompte(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.animauxVivant"), compteClient);

    }

    @CrossOrigin
    @PostMapping("updateDemande")
    @Operation(summary = "Soumission de sa demande de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> soumettreDemandeAutorisationEnlevement(
            @Valid @RequestBody AutorisationImportationUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return autorisationAnimauxVivantUpService.updateDemande(request);

    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande(
          )  {

        return autorisationAnimauxVivantClientManyService.listStatDemande();

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
}
