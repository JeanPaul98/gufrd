package com.acl.formaliteagricultureapi.controller.feedback.paiement;

import com.acl.formaliteagricultureapi.dto.feedback.paiement.FeedbackPaiementDto;
import com.acl.formaliteagricultureapi.services.feedbackPaiement.FeedbackPaiementService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/feebackpaiement")
@Tag(name = "FeedbackPaiementController",
        description = "Processus feedback")
public class FeedbackPaiementController {

    private final FeedbackPaiementService feedbackPaiementService;

    Logger logger = LoggerFactory.getLogger(FeedbackPaiementController.class);
    @Autowired
    private RetrieveValidationError retrieveValidationError;



    public FeedbackPaiementController(FeedbackPaiementService feedbackPaiementService) {
        this.feedbackPaiementService = feedbackPaiementService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour le feedback paiement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody FeedbackPaiementDto request,
            HttpServletRequest httprequest, BindingResult result) {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("request: {}", request);
        return feedbackPaiementService.create(request);

    }

}
