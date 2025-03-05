package com.acl.formaliteagricultureapi.controller.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.AgrementDto;
import com.acl.formaliteagricultureapi.services.agrement.TypeAgrementListService;
import com.acl.formaliteagricultureapi.services.agrement.AgrementService;
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
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/type/agrement")
@Tag(name = "AgrementControlleur",
        description = "Processus Type d'agrement")
public class AgrementControlleur {

    private final AgrementService agrementService;

    private final TypeAgrementListService typeAgrementListService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;
    public AgrementControlleur(AgrementService agrementService,
                               TypeAgrementListService typeAgrementListService){
        this.agrementService = agrementService;
        this.typeAgrementListService=typeAgrementListService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour le type Agrement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationEnlevement(
            @Valid @RequestBody AgrementDto agrementDto,
            HttpServletRequest httorequest, BindingResult result) {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return agrementService.createAgrement(agrementDto);

    }

    @CrossOrigin
    @GetMapping("/list")
    @Operation(summary = "Liste des types agrements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAgrements() {

        return typeAgrementListService.listTypeAgrement();

    }
}
