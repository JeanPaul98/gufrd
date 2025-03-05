package com.acl.formaliteagricultureapi.controller.export.veterinaire.animauxvivant;

import com.acl.formaliteagricultureapi.controller.export.veterinaire.cuirspeaux.AutorisationExpCuirPeauxControlleur;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant.AutorisationExpAnimauxVivantDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant.AutorisationExpAnimauxVivantCdService;
import com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant.AutorisationExpAnimauxVivantManyService;
import com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux.AutorisationExpCuirPeauxCdService;
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
@RequestMapping("api/v1/veterinaire/animauxvivant")
@Tag(name = "AutorisationExpAnimauxVivantControlleur",
        description = "Processus de formalité de demande d'exportation d'animaux vivant")
public class AutorisationExpAnimauxVivantControlleur {

    private final AutorisationExpAnimauxVivantCdService cdService;

    private final AutorisationExpAnimauxVivantManyService manyService;

    @Autowired
    private Environment env;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    Logger logger = LoggerFactory.getLogger(AutorisationExpAnimauxVivantControlleur.class);

    public AutorisationExpAnimauxVivantControlleur(AutorisationExpAnimauxVivantCdService cdService,
                                                   AutorisationExpAnimauxVivantManyService manyService) {
        this.cdService = cdService;
        this.manyService = manyService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les donnée pour export autorisation animaux vivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody AutorisationExpAnimauxVivantDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Insert autorisation enlevement request , {}", request);
        return cdService.create(request);

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
                env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return manyService.listAutorisation(Etat.SOUMIS,
                env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));

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
                env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));

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
                env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation de l'autorisation de l'enlèvement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return manyService.listAutorisation(Etat.TRAITER,
                env.getProperty("message.type.certificat.ref.veterinaire.animauxvivant"));

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
