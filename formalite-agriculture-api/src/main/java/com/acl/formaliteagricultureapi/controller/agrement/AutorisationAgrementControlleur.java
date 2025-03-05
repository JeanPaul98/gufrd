package com.acl.formaliteagricultureapi.controller.agrement;

import com.acl.formaliteagricultureapi.services.agrement.AgrementListService;
import com.acl.formaliteagricultureapi.services.agrement.AgrementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/agrement")
@Tag(name = "AutorisationAgrementControlleur",
        description = "Processus d'agrement")
public class AutorisationAgrementControlleur {

    private final AgrementListService agrementListService;

    private final AgrementService agrementService;

    public AutorisationAgrementControlleur(AgrementListService agrementListService,
                                           AgrementService agrementService) {
        this.agrementListService = agrementListService;
        this.agrementService=agrementService;
    }

    @CrossOrigin
    @GetMapping("/list")
    @Operation(summary = "Liste des agrements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAgrements() {

        return agrementListService.listAgrement();

    }

    @CrossOrigin
    @GetMapping("nif/societe")
    @Operation(summary = "Trouver les agrements par le NIF de la societe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> findByNumero(@RequestParam String nif) {

        return agrementListService.getAgrementBySociete(nif);

    }

    @CrossOrigin
    @GetMapping("numero")
    @Operation(summary = "Liste des agrements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> findByNumeroAgrement(@RequestParam String numero) {

        return agrementService.findByNumeroAgrement(numero);

    }
}
