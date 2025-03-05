package com.acl.mswauth.controller.statistique;

import com.acl.mswauth.service.statistique.StatistiqueGroupeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author kol on 10/23/24
 * @project msw-auth
 */
@RestController
@RequestMapping("api/v1/guford/statistique")
@Tag(name = "UserStatistiqueController",
        description = "Statistique de traitement")
public class UserStatistiqueController {

    private final StatistiqueGroupeService statistiqueGroupeService;

    public UserStatistiqueController(StatistiqueGroupeService statistiqueGroupeService) {
        this.statistiqueGroupeService = statistiqueGroupeService;
    }

    @CrossOrigin
    @GetMapping("groupe")
    @Operation(summary = "Statistique du nombre d'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getAllStatistiqueGroupe() throws IOException {

        return  statistiqueGroupeService.getListeStatistiqueGroupe();

    }
}
