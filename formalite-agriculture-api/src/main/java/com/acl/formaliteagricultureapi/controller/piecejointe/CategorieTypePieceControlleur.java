package com.acl.formaliteagricultureapi.controller.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.CategorieTypePieceDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import com.acl.formaliteagricultureapi.services.piecejointe.CategorieTypePieceService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zansouyé on 02/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/categorietypepiece")
@Tag(name = "CategorieTypePieceControlleur",
        description = "Processus de gestion de piece jointe")
public class CategorieTypePieceControlleur {

    Logger logger= LoggerFactory.getLogger(CategorieTypePieceControlleur.class);
    private final CategorieTypePieceService categorieTypePieceService;

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public CategorieTypePieceControlleur(CategorieTypePieceService categorieTypePieceService){
        this.categorieTypePieceService=categorieTypePieceService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insertion de la catégorie du type de pièce jointe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createTypePiece(@Valid @RequestBody CategorieTypePieceDto categorieTypePieceDto,
                                             HttpServletRequest httprequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return categorieTypePieceService.create(categorieTypePieceDto);

    }

    @CrossOrigin
    @GetMapping("allcategories")
    @Operation(summary = "Insertion de la catégorie du type de pièce jointe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTypePiece(){
        // gestion de la validation
        return categorieTypePieceService.listCategories();

    }


    @CrossOrigin
    @PutMapping("update/{id}")
    @Operation(summary = "modification de la catégorie du type de pièce jointe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "409", description = "CONFLICT: The object is  exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> updateCategories(@Valid @RequestBody CategorieTypePieceDto categorieTypePieceDto,@PathVariable long id,
                                                   HttpServletRequest httprequest, BindingResult result){
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return categorieTypePieceService.updateCategories(categorieTypePieceDto,id);

    }

}
