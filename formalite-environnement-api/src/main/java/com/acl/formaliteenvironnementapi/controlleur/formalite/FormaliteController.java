package com.acl.formaliteenvironnementapi.controlleur.formalite;



import com.acl.formaliteenvironnementapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteenvironnementapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteenvironnementapi.services.formalite.FormaliteService;
import com.acl.formaliteenvironnementapi.validator.RetrieveValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 23/08/2024
 */
@RestController
@RequestMapping("api/v1/formalite")
@Tag(name = "FormaliteController",
        description = "Processus de formalité test de soumission")
public class FormaliteController {

    private final FormaliteService formaliteService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;


    public FormaliteController(FormaliteService formaliteService) {
        this.formaliteService = formaliteService;
    }


    @CrossOrigin
    @PostMapping("soumis")
    @Operation(summary = "Test soumission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertSoumis(
            @Valid @RequestBody UpdateFormaliteDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.soumettreDemande(request);

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
        return formaliteService.annulerDemande(request);

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
            HttpServletRequest httprequest, BindingResult result) {

        // logger.info("Numero generer {} ", utilServices.generateNumDemande("test"));

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.accepterDemande(request);

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
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.rejeterDemande(request);

    }



}
