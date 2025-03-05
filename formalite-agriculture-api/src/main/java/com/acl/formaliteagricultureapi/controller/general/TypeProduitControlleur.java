package com.acl.formaliteagricultureapi.controller.general;

import com.acl.formaliteagricultureapi.dto.produit.TypeProduitDto;
import com.acl.formaliteagricultureapi.services.general.TypeProduitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */

@RestController
@RequestMapping("api/v1/typeProduit")
public class TypeProduitControlleur {

    private final TypeProduitService typeProduitService;

    public TypeProduitControlleur(TypeProduitService typeProduitService) {
        this.typeProduitService = typeProduitService;
    }

    @CrossOrigin
    @PostMapping("create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> creatTypeProduit(@RequestBody TypeProduitDto type) {
        return typeProduitService.creatTypeProduit(type);
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTypeProduit() {
        return typeProduitService.listTypeProduit();
    }
}
