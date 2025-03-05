package com.acl.formaliteagricultureapi.controller.imports.autorisation;

import com.acl.formaliteagricultureapi.dto.autorisation.LaisserPasserDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.services.autorisation.laisserPasser.LaisserPasserService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/autorisation/laisserpasser")
@Tag(name = "LaisserPasserControlleur",
        description = "Creation et generation du Jasper de LaisserPasser")
public class LaisserPasserControlleur {

    Logger logger= LoggerFactory.getLogger(LaisserPasserControlleur.class);

    private final LaisserPasserService laisserPasserService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public LaisserPasserControlleur(LaisserPasserService laisserPasserService) {
        this.laisserPasserService = laisserPasserService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'enregistrement de laisserPasser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertLaisserPasser(
            @Valid @RequestBody LaisserPasserDto laisserPasserDto,
            HttpServletRequest request, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }

        return laisserPasserService.createLaisserPasser(laisserPasserDto);

    }

    @CrossOrigin
    @GetMapping("pdf")
    @Operation(summary = "Jasper Autorisation LaisserPasser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> genereJasperAutorisation(
            @RequestParam("idFormalite") Long idFormalite) throws JRException, IOException {

        // gestion de la validation
       /* if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }*/
        UpdateFormaliteDto request= new UpdateFormaliteDto();
        request.setIdFormalite(idFormalite);
        return laisserPasserService.generateLaisserPasser(request);

    }

}
