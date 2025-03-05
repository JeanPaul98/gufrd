package com.acl.formaliteagricultureapi.controller.general;

import com.acl.formaliteagricultureapi.dto.produit.InsertProduitDto;
import com.acl.formaliteagricultureapi.services.general.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */

@RestController
@RequestMapping("/api/v1/produit")
@Tag(name = "ProduitControlleur")
public class ProduitControlleur {

    private final ProduitService produitService;

    @Autowired
    Environment env;

    public ProduitControlleur(ProduitService produitService) {
        this.produitService = produitService;
    }

    @CrossOrigin
    @PostMapping("create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> creatProduit(@RequestBody InsertProduitDto request) {
        return produitService.create(request);
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listProduit() {
        return produitService.listProduit();
    }


    @CrossOrigin
    @GetMapping("aiaa")
    @Operation(summary = "Demande produits d'aliments pour animaux vivant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listProduitaiaa() {

      return produitService.listProduitByRef(env.getProperty("message.type.autorisation.ref.alimentAnimaux"));
    }

    @CrossOrigin
    @GetMapping("aipcl")
    @Operation(summary = "Demande produits d'aliments pour produit pharmacetique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listProduitaipcl() {

        return produitService.listProduitByRef(env.getProperty("message.type.autorisation.ref.consommation"));
    }

    @CrossOrigin
    @GetMapping("aimv")
    @Operation(summary = "Demande produits d'aliments pour produit pharmacetique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listProduitaimv() {
        return produitService.listProduitByRef(env.getProperty("message.type.autorisation.ref.envmedicament"));
    }

}
