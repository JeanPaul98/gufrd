package com.acl.formaliteagricultureapi.controller.feedback.soumission;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.FeedBackUpdateDto;
import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackService;
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

import java.io.IOException;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/feeback")
@Tag(name = "FeebackPublicController",
        description = "Processus feedback")
public class FeebackSoumissionController {


    Logger logger = LoggerFactory.getLogger(FeebackSoumissionController.class);
    @Autowired
    private RetrieveValidationError retrieveValidationError;

    private final FeedbackService feedbackService;

    public FeebackSoumissionController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour le feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody FeedBackDto request,
            HttpServletRequest httprequest, BindingResult result) {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("request: {}", request);
        return feedbackService.create(request);

    }

    @CrossOrigin
    @PostMapping("update")
    @Operation(summary = "Insérer les données pour le feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> update(
            @Valid @RequestBody FeedBackUpdateDto request,
            HttpServletRequest httprequest, BindingResult result) {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("request: {}", request);
        return feedbackService.update(request);

    }


    @CrossOrigin
    @PostMapping("sendFeedBack")
    @Operation(summary = "Response d'un feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> sendFedBack(@RequestBody FeedBackFormaliteDto request) throws IOException {

        return feedbackService.sendFeedBack(request, Etat.ACCEPTER.getLabel());

    }
}
