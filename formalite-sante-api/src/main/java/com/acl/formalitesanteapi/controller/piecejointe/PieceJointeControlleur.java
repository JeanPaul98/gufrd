package com.acl.formalitesanteapi.controller.piecejointe;


import com.acl.formalitesanteapi.dto.piecejointe.FormalitePieceJointeDto;
import com.acl.formalitesanteapi.services.piecejointe.PieceJointeService;
import com.acl.formalitesanteapi.validator.RetrieveValidationError;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@RestController
@RequestMapping("api/v1/piecejointe/sante")
@Tag(name = "PieceJointeController",
        description = "Processus de gestion de piece jointe")
public class PieceJointeControlleur {

    Logger logger = LoggerFactory.getLogger(PieceJointeControlleur.class);

    private final PieceJointeService pieceJointeService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public PieceJointeControlleur(PieceJointeService pieceJointeService) {
        this.pieceJointeService = pieceJointeService;
    }

    @CrossOrigin
    @PostMapping("file")
    @Operation(summary = "Ajout des fichiers dans la formalité ministerielle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> ajouterFichier(@Valid @ModelAttribute FormalitePieceJointeDto
                                                    pieceJointeDto,
                                            HttpServletRequest httprequest, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return pieceJointeService.ajoutFile(pieceJointeDto);
    }

    @CrossOrigin
    @GetMapping("listpiece/{idFormalite}")
    @Operation(summary = "Liste les pieces jointes par formalité")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listFile(@RequestParam("idFormalite") Long idFormalite) throws JsonProcessingException {
        return pieceJointeService.listePieceJointe(idFormalite);
    }



}
