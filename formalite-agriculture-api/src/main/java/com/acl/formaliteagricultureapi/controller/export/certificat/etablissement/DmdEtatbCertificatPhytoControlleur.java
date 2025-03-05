package com.acl.formaliteagricultureapi.controller.export.certificat.etablissement;


import com.acl.formaliteagricultureapi.dto.exports.vegetaux.certificat.DmdCerticatPhytoDto;
import com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.certificat.etablissement.DmdCertificatPhytoCDService;
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

import java.io.Serializable;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/certificat/etablissement")
@Tag(name = "DmdCertificatPhytoControlleur",
        description = "Processus de demande Certificat phytosanitaire")
public class DmdEtatbCertificatPhytoControlleur implements Serializable {

    private final DmdCertificatPhytoCDService cdService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(DmdEtatbCertificatPhytoControlleur.class);

    public DmdEtatbCertificatPhytoControlleur(DmdCertificatPhytoCDService cdService) {
        this.cdService = cdService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody DmdCerticatPhytoDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return cdService.create(request);

    }
}
