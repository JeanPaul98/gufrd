package com.acl.formaliteagricultureapi.controller.redevance;

import com.acl.formaliteagricultureapi.controller.imports.autorisation.AutorisationAlimentAnimauxClientControlleur;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.FormalitePieceJointeDto;
import com.acl.formaliteagricultureapi.dto.redevance.CallbackDto;
import com.acl.formaliteagricultureapi.dto.redevance.PaiementDto;
import com.acl.formaliteagricultureapi.dto.redevance.RedevanceDto;
import com.acl.formaliteagricultureapi.services.redevance.RedevanceService;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/redevance")
@Tag(name = "RedevanceControlleur",
        description = "Paiement des redevances")
public class RedevanceControlleur {

    private final RedevanceService redevanceService;

    Logger logger = LoggerFactory.getLogger(RedevanceControlleur.class);


    @Autowired
    private UtilServices utilServices;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public RedevanceControlleur(RedevanceService redevanceService) {
        this.redevanceService = redevanceService;
    }

    @CrossOrigin
    @PostMapping("paye")
    @Operation(summary = "Paiement de redevance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> redevance(@Valid @RequestBody UpdateFormaliteDto request,
                                            HttpServletRequest httprequest, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }

        return redevanceService.paye(request);
    }

    @CrossOrigin
    @PostMapping("payement")
    @Operation(summary = "Paiement de redevance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> redevancePaiement(@Valid @RequestBody PaiementDto request,
                                       HttpServletRequest httprequest, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }

        return redevanceService.payement(request);
    }

    @CrossOrigin
    @PostMapping("callbak")
    @Operation(summary = "Reception de callback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> redevance(@Valid @RequestBody CallbackDto request,
                                       HttpServletRequest httprequest, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Reception de callback {}", request);
        return redevanceService.callbackAtd(request);
    }



}
