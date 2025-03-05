package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
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

import java.io.IOException;

/**
 * @author kol on 23/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/formalite")
@Tag(name = "FormaliteController",
        description = "Processus de formalité test de soumission")
public class FormaliteController {

    private final FormaliteService formaliteService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    @Autowired
    UtilServices utilServices;

    Logger logger = LoggerFactory.getLogger(FormaliteController.class);


    public FormaliteController(FormaliteService formaliteService) {
        this.formaliteService = formaliteService;
    }


    @CrossOrigin
    @PostMapping("soumis")
    @Operation(summary = "Soumission de la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertSoumis(
            @Valid @RequestBody UpdateFormaliteDto  request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.soumettreDemandeAutorisation(request);

    }

    @CrossOrigin
    @PostMapping("accepter")
    @Operation(summary = "Accepter la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> accepterDemande(
            @Valid @RequestBody UpdateFormaliteDto  request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

     // logger.info("Numero generer {} ", utilServices.generateNumDemande("test"));

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.accepterDemandeFeedBack(request);

    }

    @CrossOrigin
    @PostMapping("accepter/feedback")
    @Operation(summary = "Accepter la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> accepterDemandeFeedBack(
            @Valid @RequestBody UpdateFormaliteDto  request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // logger.info("Numero generer {} ", utilServices.generateNumDemande("test"));

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.accepterDemandeFeedBack(request);

    }

    @CrossOrigin
    @PostMapping("annuler")
    @Operation(summary = "Annuler la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> annuler(
            @Valid @RequestBody UpdateFormaliteDto  request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.annulerDemandeAutorisation(request);

    }


    @CrossOrigin
    @PostMapping("rejeter")
    @Operation(summary = "Rejeter  la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> rejeter(
            @Valid @RequestBody RejetFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.rejeterDemandeFeedback(request);

    }

    @CrossOrigin
    @PostMapping("rejeter/feedback")
    @Operation(summary = "Rejeter  la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> rejeterFeedBack(
            @Valid @RequestBody RejetFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.rejeterDemandeFeedback(request);

    }

    @CrossOrigin
    @PostMapping("confirm/paiement")
    @Operation(summary = "Confirmer le paiement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> confirmPaiement(
            @Valid @RequestBody UpdateFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Request {}", request);
        return formaliteService.confirmPaiement(request);

    }

    @CrossOrigin
    @PostMapping("confirm/paiementPv")
    @Operation(summary = "Confirmer le paiement du process verbal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> confirmPaiementPv(
            @Valid @RequestBody UpdateFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Request {}", request);
        return formaliteService.confirmPaiementPhyto(request);

    }


    @CrossOrigin
    @PostMapping("confirm/certificat")
    @Operation(summary = "Confirmer le paiement du certificat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> confirmCertificat(
            @Valid @RequestBody UpdateFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) throws IOException {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Request {}", request);
        return formaliteService.confirmCertificat(request);

    }


}
