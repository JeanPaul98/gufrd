package com.acl.formaliteagricultureapi.controller.imports.pv;

import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import com.acl.formaliteagricultureapi.services.processVerval.cargaison.PvPhytoCargaisonManyService;
import com.acl.formaliteagricultureapi.services.processVerval.cargaison.PvPhytoCargaisonService;
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
 * @author kol on 07/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/pv/phytoCargo")
@Tag(name = "PvPhytoCargaisonControlleur",
        description = "Processus de procès verbal pour cargaison ")
public class PvPhytoCargaisonControlleur {

    private final PvPhytoCargaisonService pvPhytoCargaisonService;

    private final PvPhytoCargaisonManyService manyService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    @Autowired
    private Environment env;

    public PvPhytoCargaisonControlleur(PvPhytoCargaisonService pvPhytoCargaisonService, PvPhytoCargaisonManyService manyService) {
        this.pvPhytoCargaisonService = pvPhytoCargaisonService;
        this.manyService = manyService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour la delivrance du pv phytosanitaire de cargaison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createPhytoNavire(@Valid @RequestBody PvPhytoSanitaireCargConteneurDto
                                                       request,
                                               HttpServletRequest httrequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return pvPhytoCargaisonService.create(request);

    }

    @CrossOrigin
    @GetMapping("listeProcesVerbale")
    @Operation(summary = "Insérer les données pour la delivrance du pv phytosanitaire de navie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeProcesVerbal(){
        // gestion de la validation
        return manyService.listProcessVervale(env.getProperty("message.type.pv.ref.cargaison"));

    }

}
