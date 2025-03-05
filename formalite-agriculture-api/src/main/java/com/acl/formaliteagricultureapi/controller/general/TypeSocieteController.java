package com.acl.formaliteagricultureapi.controller.general;

import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import com.acl.formaliteagricultureapi.dto.societe.TypeSocieteDto;
import com.acl.formaliteagricultureapi.services.general.TypeSocieteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/typesociete")
@Tag(name = "TypeSocieteController")
public class TypeSocieteController {

    private final TypeSocieteService typeSocieteService;

    public TypeSocieteController(TypeSocieteService typeSocieteService) {
        this.typeSocieteService = typeSocieteService;
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSociete() {
        return typeSocieteService.listTypeSociete();
    }


    @CrossOrigin
    @PostMapping("create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> addSociete(@RequestBody TypeSocieteDto typeSocieteDto) {
        return typeSocieteService.addTypeSociete(typeSocieteDto);
    }


    @CrossOrigin
    @PutMapping("update/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> updateSociete(@PathVariable long id,@RequestBody TypeSocieteDto typeSocieteDto) {
        return typeSocieteService.updateTypeSociete(typeSocieteDto,id);
    }
}
