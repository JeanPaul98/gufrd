package com.acl.formaliteagricultureapi.controller.export.veterinaire.cuirspeaux;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux.AutorisationExpCuirPeauxCdService;
import com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux.AutorisationExpCuirPeauxManyService;
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

import java.io.Serializable;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/veterinaire/cuirspeaux")
@Tag(name = "AutorisationExpCuirPeauxControlleur",
        description = "Processus de formalité de demande certificat pour cuirs et peaux")
public class AutorisationExpCuirPeauxControlleur implements Serializable {

    private final AutorisationExpCuirPeauxCdService cuirPeauxCdService;

    private final AutorisationExpCuirPeauxManyService manyService;

    Logger logger = LoggerFactory.getLogger(AutorisationExpCuirPeauxControlleur.class);

    public AutorisationExpCuirPeauxControlleur(AutorisationExpCuirPeauxCdService cuirPeauxCdService,
                                               AutorisationExpCuirPeauxManyService manyService) {
        this.cuirPeauxCdService = cuirPeauxCdService;
        this.manyService = manyService;
    }

    @Autowired
    private Environment env;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour cuire et peaux")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody AutorisationExpCuirePeauxDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Insert autorisation enlevement request , {}", request);
        return cuirPeauxCdService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return manyService.listAutorisation(Etat.NON_SOUMIS,
                env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));

    }


    @CrossOrigin
    @GetMapping("soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return manyService.listAutorisation(Etat.SOUMIS,
                env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return manyService.listAutorisation(Etat.REJETER,
                env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return manyService.listAutorisation(Etat.ACCEPTER,
                env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour les demandes traités")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return manyService.listAutorisation(Etat.TRAITER,
                env.getProperty("message.type.certificat.ref.veterinaire.cuirspeaux"));

    }





    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return manyService.listStatDemande();

    }



}
