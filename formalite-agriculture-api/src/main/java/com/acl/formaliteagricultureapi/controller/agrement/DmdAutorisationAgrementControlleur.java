package com.acl.formaliteagricultureapi.controller.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.*;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.agrement.DemandeAutorisationAgrementCreateService;
import com.acl.formaliteagricultureapi.services.agrement.DemandeAutorisationAgrementListService;
import com.acl.formaliteagricultureapi.services.agrement.DmdAgrementUpdateService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
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
 * @author Zansouyé on 04/10/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/demande/agrement")
@Tag(name = "DmdAutorisationAgrementControlleur",
        description = "Processus Demande d'autorisation d'agrément")
public class DmdAutorisationAgrementControlleur {

    private final DemandeAutorisationAgrementCreateService demandeAutorisationAgrementCreateService;

    private final DemandeAutorisationAgrementListService demandeAutorisationAgrementListService;

    private final DmdAgrementUpdateService dmdAgrementUpdateService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public DmdAutorisationAgrementControlleur(DemandeAutorisationAgrementCreateService
                                                      demandeAutorisationAgrementCreateService,
                                              DemandeAutorisationAgrementListService
                                                      demandeAutorisationAgrementListService, DmdAgrementUpdateService dmdAgrementUpdateService){
        this.demandeAutorisationAgrementCreateService=demandeAutorisationAgrementCreateService;
        this.demandeAutorisationAgrementListService=demandeAutorisationAgrementListService;
        this.dmdAgrementUpdateService = dmdAgrementUpdateService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour le type Agrement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody DemandeAutorisationAgrementDto demandeAutorisationAgrementDto,
            HttpServletRequest httorequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return demandeAutorisationAgrementCreateService.
                createDemandeAutorisationAgrement(demandeAutorisationAgrementDto);

    }

    @CrossOrigin
    @PostMapping("existant")
    @Operation(summary = "Insérer les données pour le type Agrement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertSansPiecejointe(
            @Valid @RequestBody DemandeAutorisationAgrementDto demandeAutorisationAgrementDto,
            HttpServletRequest httorequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return demandeAutorisationAgrementCreateService.
                createDemandeAutorisationAgrementSansPieceJointe(demandeAutorisationAgrementDto);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste des agréments non soumis par un compte demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {

        return demandeAutorisationAgrementListService.listAutorisationAgrements(Etat.NON_SOUMIS);

    }

    @CrossOrigin
    @GetMapping("soumis")
    @Operation(summary = "Liste des agréments  soumis par un compte demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumise() {

        return demandeAutorisationAgrementListService.listAutorisationAgrements(Etat.SOUMIS);

    }

    @CrossOrigin
    @GetMapping("traite")
    @Operation(summary = "Liste des agréments traités par un compte demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return demandeAutorisationAgrementListService.listAutorisationAgrements(Etat.TRAITER);

    }

    @CrossOrigin
    @GetMapping("rejeter")
    @Operation(summary = "Liste des agréments traités par un compte demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return demandeAutorisationAgrementListService.listAutorisationAgrements(Etat.REJETER);

    }

    @CrossOrigin
    @GetMapping("accepte")
    @Operation(summary = "Liste des agréments acceptés par un compte demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return demandeAutorisationAgrementListService.listAutorisationAgrements(Etat.ACCEPTER);

    }

    @CrossOrigin
    @PostMapping("soumis")
    @Operation(summary = "Soumission de la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertSoumis(
            @Valid @RequestBody UpdateDemandeAutorisationAgrement request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return demandeAutorisationAgrementCreateService.soumettreDemandeAutorisationAgrement(request);

    }

    @CrossOrigin
    @PostMapping("traiter")
    @Operation(summary = "Traiter de la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertTraiter(
            @Valid @RequestBody DmdAgrementUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return dmdAgrementUpdateService.traiter(request);

    }

    @CrossOrigin
    @PostMapping("rejeter")
    @Operation(summary = "Rejeter de la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> rejet(
            @Valid @RequestBody DmdAgrementUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return dmdAgrementUpdateService.rejecter(request);

    }

    @CrossOrigin
    @PostMapping("accepter")
    @Operation(summary = "Accepter de la demande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> accepter(
            @Valid @RequestBody DmdAgrementUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return dmdAgrementUpdateService.accepter(request);

    }

}
