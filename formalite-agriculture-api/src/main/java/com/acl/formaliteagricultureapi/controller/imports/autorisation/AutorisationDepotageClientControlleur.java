package com.acl.formaliteagricultureapi.controller.imports.autorisation;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientDto;
import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitCRUDService;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitClientUpService;
import com.acl.formaliteagricultureapi.services.autorisation.depotage.AutorisationDepotageTransitManyClientService;
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
 * @author kol on 19/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/autorisation/depotage")
@Tag(name = "AutorisationDepotageControlleur",
        description = "Processus de formalité \n" +
                "Autorisation de dépotage et de transit\n")
public class AutorisationDepotageClientControlleur {

    Logger logger = LoggerFactory.getLogger(AutorisationDepotageClientControlleur.class);

    private  final AutorisationDepotageTransitCRUDService autorisationDepotageTransitCRUDService;

    private final AutorisationDepotageTransitManyClientService autorisationDepotageTransitManyClientService;

    private final FormaliteService formaliteService;

    private final AutorisationDepotageTransitClientUpService autorisationDepotageTransitClientUpService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public AutorisationDepotageClientControlleur(AutorisationDepotageTransitCRUDService
                                                         autorisationDepotageTransitCRUDService,
                                                 AutorisationDepotageTransitManyClientService
                                                         autorisationDepotageTransitManyClientService,
                                                 FormaliteService formaliteService, AutorisationDepotageTransitClientUpService autorisationDepotageTransitClientUpService) {
        this.autorisationDepotageTransitCRUDService = autorisationDepotageTransitCRUDService;
        this.autorisationDepotageTransitManyClientService = autorisationDepotageTransitManyClientService;
        this.formaliteService=formaliteService;
        this.autorisationDepotageTransitClientUpService = autorisationDepotageTransitClientUpService;
    }

    @CrossOrigin
    @PostMapping("/insert")
    @Operation(summary = "Insérer les données pour l'autorisation de l'autorisation de depotage transit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationDepotage(
            @Valid @RequestBody AutorisationDepotageTransitClientDto autorisationDepotageTransitClientDto,
            HttpServletRequest request, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return autorisationDepotageTransitCRUDService.create(autorisationDepotageTransitClientDto);

    }


    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return autorisationDepotageTransitManyClientService.listAutorisationDepotage(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("nonsoumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumiseClient(@RequestParam String compteClient) {

        return autorisationDepotageTransitManyClientService.listAutorisatioByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.depotage"),compteClient);

    }

    @CrossOrigin
    @GetMapping("soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return autorisationDepotageTransitManyClientService.listAutorisationDepotage(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisClient(@RequestParam String compteClient) {

        return autorisationDepotageTransitManyClientService.listAutorisatioByCompte(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.depotage"),compteClient);

    }


    @CrossOrigin
    @GetMapping("/rejetee")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return autorisationDepotageTransitManyClientService.listAutorisationDepotage(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("rejetee/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter(@RequestParam String compte) {

        return autorisationDepotageTransitManyClientService.listAutorisatioByCompte(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.depotage"), compte);

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return autorisationDepotageTransitManyClientService.listAutorisationDepotage(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("accepter/client")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepterClient(@RequestParam String compte) {

        return autorisationDepotageTransitManyClientService.listAutorisatioByCompte(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.depotage"),compte);

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return autorisationDepotageTransitManyClientService.listAutorisationDepotage(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("traiter/client")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de dépotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiterByClient(@RequestParam String compte) {

        return autorisationDepotageTransitManyClientService.listAutorisatioByCompte(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.depotage"), compte);

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
            @Valid @RequestBody AutorisationDepotageTransitClientListDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("update demande json "+request);
        return autorisationDepotageTransitClientUpService.updateDemande(request);

    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return autorisationDepotageTransitManyClientService.listStatDemande();

    }


}
