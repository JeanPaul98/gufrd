package com.acl.formaliteenvironnementapi.controlleur;

import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvDechetDto;
import com.acl.formaliteenvironnementapi.dto.enlevement.EnlevementDechetDto;
import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import com.acl.formaliteenvironnementapi.services.enlevement.EnlevementDechetService;
import com.acl.formaliteenvironnementapi.services.enlevement.EnlevementDechetsManyService;
import com.acl.formaliteenvironnementapi.validator.RetrieveValidationError;
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
 * @author kol on 12/09/2024
 * @project formalite-environnement-api
 */
@RestController
@RequestMapping("api/v1/autorisation/enlevement")
@Tag(name = "EnlevementDechetControlleur",
        description = "Processus de demande  d'enlevement de déchets")
public class EnlevementDechetControlleur {

    private final EnlevementDechetService cdService;

    private final EnlevementDechetsManyService manyService;

    Logger logger = LoggerFactory.getLogger(EnlevementDechetControlleur.class);


    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public EnlevementDechetControlleur(EnlevementDechetService cdService, EnlevementDechetsManyService manyService) {
        this.cdService = cdService;
        this.manyService = manyService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody EnlevementDechetDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Request {}, {}", request, result);
        return cdService.create(request);
    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour dechets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return manyService.listAutorisation(Etat.NON_SOUMIS,
                env.getProperty("message.type.autorisation.ref.enlevement"));

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return manyService.listAutorisation(Etat.SOUMIS,
                env.getProperty("message.type.autorisation.ref.enlevement"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return manyService.listAutorisation(Etat.REJETER,
                env.getProperty("message.type.autorisation.ref.enlevement"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return manyService.listAutorisation(Etat.ACCEPTER,
                env.getProperty("message.type.autorisation.ref.enlevement"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return manyService.listAutorisation(Etat.TRAITER,
                env.getProperty("message.type.autorisation.ref.enlevement"));

    }

    @CrossOrigin
    @GetMapping("statDemande")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listStatDemande() {

        return manyService.listStatDemande();

    }

}
