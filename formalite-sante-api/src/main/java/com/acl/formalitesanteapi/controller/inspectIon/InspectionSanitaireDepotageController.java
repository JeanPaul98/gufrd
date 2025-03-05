package com.acl.formalitesanteapi.controller.inspectIon;

import com.acl.formalitesanteapi.dto.inspection.depotage.InspectionSanitaireDepotageDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.services.inspection.depotage.InspectionSanitaireDepotageListService;
import com.acl.formalitesanteapi.services.inspection.depotage.InspectionSanitaireDepotageService;
import com.acl.formalitesanteapi.validator.RetrieveValidationError;
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

import java.io.Serializable;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@RestController
@RequestMapping("api/v1/inspection/depotage")
@Tag(name = "InspectionSanitaireDepotageController",
        description = "Processus de demande depotage")
public class InspectionSanitaireDepotageController {

    private final InspectionSanitaireDepotageService service;

    private final InspectionSanitaireDepotageListService listService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(InspectionSanitaireDepotageController.class);

    public InspectionSanitaireDepotageController(InspectionSanitaireDepotageService service,
                                                 InspectionSanitaireDepotageListService listService) {
        this.service = service;
        this.listService = listService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody InspectionSanitaireDepotageDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return service.create(request);

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
        return listService.listInspection(Etat.NON_SOUMIS,
                env.getProperty("message.type.inspection.ref.depotage"));

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return listService.listInspection(Etat.SOUMIS,
                env.getProperty("message.type.inspection.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return listService.listInspection(Etat.REJETER,
                env.getProperty("message.type.inspection.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return listService.listInspection(Etat.ACCEPTER,
                env.getProperty("message.type.inspection.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return listService.listInspection(Etat.TRAITER,
                env.getProperty("message.type.inspection.ref.depotage"));

    }

    @CrossOrigin
    @GetMapping("statDemande")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listStatDemande() {

        return listService.listStatDemande();

    }
}
