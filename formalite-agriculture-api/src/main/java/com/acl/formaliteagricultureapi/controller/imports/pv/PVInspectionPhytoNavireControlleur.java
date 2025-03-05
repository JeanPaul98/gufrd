package com.acl.formaliteagricultureapi.controller.imports.pv;

import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireDto;
import com.acl.formaliteagricultureapi.services.processVerval.navire.PvPhytoNavireManyService;
import com.acl.formaliteagricultureapi.services.processVerval.navire.PvPhytoNavireService;
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

/**
 * @author Zansouyé on 26/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/pv/phytonavire")
@Tag(name = "PVInspectionPhytoNavireControlleur",
        description = "Processus de procès verbal pour l'inspection de phytosanitaire de navire ")
public class PVInspectionPhytoNavireControlleur {

    private Logger logger= LoggerFactory.getLogger(PVInspectionPhytoNavireControlleur.class);

    private final PvPhytoNavireService pvPhytoNavireService;

    private final PvPhytoNavireManyService pvPhytoNavireManyService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;
    public PVInspectionPhytoNavireControlleur(PvPhytoNavireService pvPhytoNavireService, PvPhytoNavireManyService pvPhytoNavireManyService){
        this.pvPhytoNavireService=pvPhytoNavireService;
        this.pvPhytoNavireManyService = pvPhytoNavireManyService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour la delivrance du pv phytosanitaire de navie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createPhytoNavire(@Valid @RequestBody PvPhytoSanitaireNavireDto
                                                           pvPhytoSanitaireNavireDto,
                                               HttpServletRequest request, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("Phytosanitaire {}", pvPhytoSanitaireNavireDto);
        return pvPhytoNavireService.create(pvPhytoSanitaireNavireDto);

    }

    @CrossOrigin
    @GetMapping("listeProcesVerbale")
    @Operation(summary = "Insérer les données pour la delivrance du pv phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeProcesVerbal(){
        // gestion de la validation
        return pvPhytoNavireManyService.listProcessVervale(env.getProperty("message.type.pv.ref.navire"));

    }
}
