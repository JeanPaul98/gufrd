package com.acl.formaliteagricultureapi.controller.imports.phytosanitaire;

import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonUpClientDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison.InspectionPhtyoCargaisonManyService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison.InspectionPhytoCargaisonClientCrudService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison.InspectionPhytoCargaisonUpService;
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
 * @author kol on 24/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/phytosanitaire/cargaison")
@Tag(name = "InspectionPhytoCargaisonControlleur",
        description = "Processus de formalité d'inspection phytosanitaire produits cargaison")
public class InspectionPhytoCargaisonControlleur {


    private final InspectionPhytoCargaisonClientCrudService inspectionPhytoCargaisonClientCrudService;

    private final InspectionPhtyoCargaisonManyService inspectionPhtyoCargaisonManyService;

    private final InspectionPhytoCargaisonUpService inspectionPhytoCargaisonUpService;

    private final FormaliteService formaliteService;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(InspectionPhytoCargaisonControlleur.class);

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public InspectionPhytoCargaisonControlleur(InspectionPhytoCargaisonClientCrudService
                                                       inspectionPhytoCargaisonClientCrudService,
                                               InspectionPhtyoCargaisonManyService
                                                       inspectionPhtyoCargaisonManyService, InspectionPhytoCargaisonUpService inspectionPhytoCargaisonUpService,
                                               FormaliteService formaliteService) {
        this.inspectionPhytoCargaisonClientCrudService = inspectionPhytoCargaisonClientCrudService;
        this.inspectionPhtyoCargaisonManyService = inspectionPhtyoCargaisonManyService;
        this.inspectionPhytoCargaisonUpService = inspectionPhytoCargaisonUpService;
        this.formaliteService=formaliteService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire cargaison de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationEnlevement(
            @Valid @RequestBody PhytosanitaireCargaisonClientDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return inspectionPhytoCargaisonClientCrudService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }

    @CrossOrigin
    @GetMapping("nonsoumis/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumiseClient(@RequestParam("compteClient") String compte) {
        logger.info("Liste non soumise envoie ");
        return inspectionPhtyoCargaisonManyService.listPhytocargaisonByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"), compte);

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisClient(@RequestParam("compteClient") String compte) {

        return inspectionPhtyoCargaisonManyService.listPhytocargaisonByCompte(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"), compte);

    }


    @CrossOrigin
    @GetMapping("/all")
    @Operation(summary = "Liste les données pour phytosanitaire administration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumisAll() {

        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.REJETER,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }

    @CrossOrigin
    @GetMapping("accepter/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepterClient(@RequestParam("compteClient") String compte) {

        return inspectionPhtyoCargaisonManyService.listPhytocargaisonByCompte(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"),compte);

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }


    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour traité pour l'inspection phyto cargaison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return inspectionPhtyoCargaisonManyService.listAutorisationEnlevement(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"));

    }


    @CrossOrigin
    @GetMapping("traiter/client")
    @Operation(summary = "liste  les données pour traité pour l'inspection phyto cargaison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiterClient(@RequestParam("compteClient") String compte) {

        return inspectionPhtyoCargaisonManyService.listPhytocargaisonByCompte(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.cargaison"), compte);

    }

    @CrossOrigin
    @PostMapping("updateDemande")
    @Operation(summary = "Mise a jour  de la demande inspection cargaison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> soumettre(
            @Valid @RequestBody PhytosanitaireCargaisonUpClientDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return inspectionPhytoCargaisonUpService.updateDemande(request);

    }


    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return inspectionPhtyoCargaisonManyService.listStatDemande();

    }
}
