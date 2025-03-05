package com.acl.formaliteagricultureapi.controller.imports.pv;

import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoPharmaDto;
import com.acl.formaliteagricultureapi.services.processVerval.pharma.PvPhytoProduitPharmaService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 27/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/pv/phytoPharma")
@Tag(name = "PvInspPhytoPharmaControlleur",
        description = "Processus de procès verbal pour l'inspection de phyto pharmaceutique ")
public class PvInspPhytoPharmaControlleur {

    private final PvPhytoProduitPharmaService pvPhytoProduitPharmaService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public PvInspPhytoPharmaControlleur(PvPhytoProduitPharmaService pvPhytoProduitPharmaService) {
        this.pvPhytoProduitPharmaService = pvPhytoProduitPharmaService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour la delivrance du pv phytosanitaire de navie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createPhytoNavire(@Valid @RequestBody PvPhytoPharmaDto
                                                       request,
                                               HttpServletRequest httrequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return pvPhytoProduitPharmaService.create(request);

    }


}
