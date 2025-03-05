package com.acl.formaliteagricultureapi.controller.export.phytosanitaire;

import com.acl.formaliteagricultureapi.controller.imports.autorisation.AutorisationAlimentAnimauxClientControlleur;
import com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection.InspectionPhytoObtentionCertifDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif.InspectionPhytoObtentionCertCrudService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif.InspectionPhytoObtentionManyService;
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
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
/*
@RestController
 */
@RequestMapping("api/v1/phytosanitaire/obtentionCertificat")
@Tag(name = "InspectionPhytoObtentionCertificatControlleur",
        description = "Processus de formalité de demande d'inspection phytosanitaie")
public class InspectionPhytoObtentionCertificatControlleur {

    Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientControlleur.class);

    private final InspectionPhytoObtentionCertCrudService serviceCrud;

    private final InspectionPhytoObtentionManyService serviceMany;


    private Environment env;



    private RetrieveValidationError retrieveValidationError;

    public InspectionPhytoObtentionCertificatControlleur(InspectionPhytoObtentionCertCrudService serviceCrud,
                                                         InspectionPhytoObtentionManyService serviceMany) {
        this.serviceCrud = serviceCrud;
        this.serviceMany = serviceMany;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour obtention l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationEnlevement(
            @Valid @RequestBody InspectionPhytoObtentionCertifDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Insert autorisation enlevement request , {}", request);
        return serviceCrud.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return serviceMany.listPhytosanitaire(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.obtencertif"));

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return serviceMany.listPhytosanitaire(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.obtencertif"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour l'autorisation d'importation de produit à consommation local rejetée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return serviceMany.listPhytosanitaire(Etat.REJETER,
                env.getProperty("message.type.phytosanitaire.ref.obtencertif"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation d'importation de produit à consommation local Accepter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return serviceMany.listPhytosanitaire(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.obtencertif"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return serviceMany.listPhytosanitaire(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.obtencertif"));

    }
}
