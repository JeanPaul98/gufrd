package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.services.formalite.GenereAutorisationService;
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
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/report")
@Tag(name = "GenereAutorisationControlleur",
        description = "Processus pour générer le jasper des autorisations")
public class GenereAutorisationControlleur {

    Logger logger= LoggerFactory.getLogger(GenereAutorisationControlleur.class);

    private final GenereAutorisationService genereAutorisationService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;
    public GenereAutorisationControlleur(GenereAutorisationService genereAutorisationService) {
        this.genereAutorisationService = genereAutorisationService;
    }

    @CrossOrigin
    @GetMapping("autorisation/pdf")
    @Operation(summary = "Jasper Autorisation")
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

        return genereAutorisationService.genereAutorisation(request);

    }
}
