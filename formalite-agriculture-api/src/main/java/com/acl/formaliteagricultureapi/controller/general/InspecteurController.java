package com.acl.formaliteagricultureapi.controller.general;


import com.acl.formaliteagricultureapi.services.general.InspecteurService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inspecteur")
@Tag(name = "InspecteurController")
public class InspecteurController {

    private final InspecteurService inspecteurService;

    public InspecteurController(InspecteurService inspecteurService) {
        this.inspecteurService = inspecteurService;
    }

    @CrossOrigin
    @GetMapping("list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listInspecteurs() {
        return inspecteurService.listInspecteurs();
    }
}
