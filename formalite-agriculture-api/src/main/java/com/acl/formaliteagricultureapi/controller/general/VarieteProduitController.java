package com.acl.formaliteagricultureapi.controller.general;

import com.acl.formaliteagricultureapi.dto.produit.VarieteProduitDto;
import com.acl.formaliteagricultureapi.services.general.VarieteProduitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ varieteProduit")
@Tag(name = "StructureController")
public class VarieteProduitController {

    private final VarieteProduitService varieteProduitService;

    public VarieteProduitController(VarieteProduitService varieteProduitService) {
        this.varieteProduitService = varieteProduitService;
    }

    @CrossOrigin
    @PostMapping("create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> createVariete(@RequestBody VarieteProduitDto request) {
        return varieteProduitService.createVarieteProduit(request);
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listVariete() {
        return varieteProduitService.listVarieteProduit();
    }
}
