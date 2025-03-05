package com.acl.mswauth.controller.statistique;

import com.acl.mswauth.service.statistique.StatistiqueFormaliteService;
import com.acl.mswauth.service.statistique.StatistiqueGroupeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@RestController
@RequestMapping("api/v1/statistique")
@Tag(name = "StatistiqueFormaliteController",
        description = "Statistique de traitement")
public class StatistiqueFormaliteController {

    private final StatistiqueFormaliteService statistiqueFormaliteService;

    public StatistiqueFormaliteController(StatistiqueFormaliteService statistiqueFormaliteService) {
        this.statistiqueFormaliteService = statistiqueFormaliteService;
    }


    @CrossOrigin
    @GetMapping("phytosanitaire")
    @Operation(summary = "Statisque demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getAllDemandeTraiterPhyto() throws IOException {

        return  statistiqueFormaliteService.getFormaliteTraiterPhyto();

    }

    @CrossOrigin
    @GetMapping("phytosanitaire/all")
    @Operation(summary = "Statisque Phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getAllDemandeTraiterPhytoPageable(@RequestParam int page, @RequestParam int size) throws IOException {

        return  statistiqueFormaliteService.getFormalitePageable(page, size);

    }

}
