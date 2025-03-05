package com.acl.formaliteagricultureapi.controller.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.FormalitePieceJointeDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import com.acl.formaliteagricultureapi.services.piecejointe.TypePieceJointeService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
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
 * @author Zansouyé on 02/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/typepiecejointe")
@Tag(name = "TypePieceJointeControlleur",
        description = "Processus de gestion de type piece jointe")
public class TypePieceJointeControlleur {

    Logger logger= LoggerFactory.getLogger(TypePieceJointeControlleur.class);

    private final TypePieceJointeService typePieceJointeService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public TypePieceJointeControlleur(TypePieceJointeService typePieceJointeService){
        this.typePieceJointeService=typePieceJointeService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Ajout des fichiers dans la formalité ministerielle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createTypePiece(@Valid @RequestBody TypePieceJointeDto typePieceJointeDto,
                                             HttpServletRequest httprequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return typePieceJointeService.create(typePieceJointeDto);

    }


    @CrossOrigin
    @GetMapping("list")
    @Operation(summary = "Liste la listes des types de pièces jointes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTypePieceJointe(){
        // gestion de la validation

        return typePieceJointeService.list();

    }

//    @CrossOrigin
//    @GetMapping("category/{ref}")
//    @Operation(summary = "Liste les pieces jointes par formalité")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
//            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
//    public ResponseEntity<?> listPieceByRef(@RequestParam("ref") String ref) throws JsonProcessingException {
//        return typePieceJointeService.findTypeByCategory(ref);
//    }


    @CrossOrigin
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Supprimer les pieces jointes par formalité")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listPieceByRef(@PathVariable long id){
        return typePieceJointeService.deleteTypePieceJointe(id);
    }


    @CrossOrigin
    @PutMapping("update/{id}")
    @Operation(summary = "modification des fichiers dans la formalité ministerielle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> updateTypePieceJointe(@Valid @RequestBody TypePieceJointeDto typePieceJointeDto,@PathVariable long id,
                                             HttpServletRequest httprequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return typePieceJointeService.updateTypePieceJointe(typePieceJointeDto,id);

    }

}
