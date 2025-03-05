package com.acl.formalitesanteapi.controller.inspectIon;

import com.acl.formalitesanteapi.dto.inspection.desinfection.DemandeDesinfectionDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireDto;
import com.acl.formalitesanteapi.models.enumeration.Etat;
import com.acl.formalitesanteapi.services.inspection.desinfection.DemandeDesinfectionManyService;
import com.acl.formalitesanteapi.services.inspection.desinfection.DemandeDesinfectionSanitaireService;
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

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@RestController
@RequestMapping("api/v1/inspection/desinfection")
@Tag(name = "InspectionDesinfectionControlleur",
        description = "Processus de demande d'inspection navire")
public class InspectionDesinfectionControlleur {

    private final DemandeDesinfectionSanitaireService desinfectionSanitaireService ;

    private final DemandeDesinfectionManyService demandeDesinfectionManyService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(InspectionDesinfectionControlleur.class);

    public InspectionDesinfectionControlleur(DemandeDesinfectionSanitaireService desinfectionSanitaireService, DemandeDesinfectionManyService demandeDesinfectionManyService) {
        this.desinfectionSanitaireService = desinfectionSanitaireService;
        this.demandeDesinfectionManyService = demandeDesinfectionManyService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody DemandeDesinfectionDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return desinfectionSanitaireService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour desinfection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return demandeDesinfectionManyService.listInspection(Etat.NON_SOUMIS,
                env.getProperty("message.type.inspection.ref.desinfection"));

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour desinfection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return demandeDesinfectionManyService.listInspection(Etat.SOUMIS,
                env.getProperty("message.type.inspection.ref.desinfection"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return demandeDesinfectionManyService.listInspection(Etat.REJETER,
                env.getProperty("message.type.inspection.ref.desinfection"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour desinfection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return demandeDesinfectionManyService.listInspection(Etat.ACCEPTER,
                env.getProperty("message.type.inspection.ref.desinfection"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return demandeDesinfectionManyService.listInspection(Etat.TRAITER,
                env.getProperty("message.type.inspection.ref.desinfection"));

    }

    @CrossOrigin
    @GetMapping("statDemande")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listStatDemande() {

        return demandeDesinfectionManyService.listStatDemande();

    }

}
