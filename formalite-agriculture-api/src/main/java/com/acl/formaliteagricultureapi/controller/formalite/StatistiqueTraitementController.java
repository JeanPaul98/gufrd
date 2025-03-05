package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteStatistiqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/statistique")
@Tag(name = "StatistiqueTraitementController",
        description = "Processus de formalité test de soumission")
public class StatistiqueTraitementController {

    private final FormaliteStatistiqueService formaliteStatistiqueService;


    public StatistiqueTraitementController(FormaliteStatistiqueService formaliteStatistiqueService) {
        this.formaliteStatistiqueService = formaliteStatistiqueService;
    }

    @CrossOrigin
    @GetMapping("phytosanitaire")
    @Operation(summary = "Statisque demandeur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> getAllDemandeTraiter() {

        return  formaliteStatistiqueService.getAllFormaliteStatistiquePhyto();

    }


}
