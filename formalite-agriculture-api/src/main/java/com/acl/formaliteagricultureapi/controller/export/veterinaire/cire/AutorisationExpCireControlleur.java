package com.acl.formaliteagricultureapi.controller.export.veterinaire.cire;

import com.acl.formaliteagricultureapi.controller.export.veterinaire.cuirspeaux.AutorisationExpCuirPeauxControlleur;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire.AutorisationExpCireDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.veterinaire.cire.AutorisationExpCireCdService;
import com.acl.formaliteagricultureapi.services.veterinaire.cire.AutorisationExpCireManyService;
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
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/veterinaire/cire")
@Tag(name = "AutorisationExpCireControlleur",
        description = "Processus de formalité de demande d'exportation pour cire")
public class AutorisationExpCireControlleur {

    Logger logger = LoggerFactory.getLogger(AutorisationExpCireControlleur.class);

    private final AutorisationExpCireCdService expCireCdService;

    private final AutorisationExpCireManyService manyExpCireCdService;

    @Autowired
    private Environment env;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public AutorisationExpCireControlleur(AutorisationExpCireCdService expCireCdService,
                                          AutorisationExpCireManyService manyExpCireCdService) {
        this.expCireCdService = expCireCdService;
        this.manyExpCireCdService = manyExpCireCdService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour obtention l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody AutorisationExpCireDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Insert autorisation enlevement request , {}", request);
        return expCireCdService.create(request);

    }

    @CrossOrigin
    @GetMapping("/nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return manyExpCireCdService.listeAutorisation(Etat.NON_SOUMIS,
                env.getProperty("message.type.certificat.ref.veterinaire.cire"));

    }

    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return manyExpCireCdService.listeAutorisation(Etat.SOUMIS,
                env.getProperty("message.type.certificat.ref.veterinaire.cire"));

    }


    @CrossOrigin
    @GetMapping("/rejetee")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de depotage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return manyExpCireCdService.listeAutorisation(Etat.REJETER,
                env.getProperty("message.type.certificat.ref.veterinaire.cire"));

    }

    @CrossOrigin
    @GetMapping("/accepter")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return manyExpCireCdService.listeAutorisation(Etat.ACCEPTER,
                env.getProperty("message.type.certificat.ref.veterinaire.cire"));

    }

    @CrossOrigin
    @GetMapping("/traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return manyExpCireCdService.listeAutorisation(Etat.TRAITER,
                env.getProperty("message.type.certificat.ref.veterinaire.cire"));

    }


    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return manyExpCireCdService.listStatDemande();

    }

}
