package com.acl.vbs.controllers;

import com.acl.vbs.requests.BonEntreeCamionRequest;
import com.acl.vbs.requests.BonEntreeCamionUpdatePoidsRequest;
import com.acl.vbs.responses.HttpResponse;
import com.acl.vbs.services.BonEntreeCamionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.acl.vbs.utils.AppUtils.API_BASE_URL;
import static com.acl.vbs.utils.AppUtils.successResponse;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = API_BASE_URL + "/declarations", produces = {APPLICATION_JSON_VALUE})
public class BonEntreeCamionController {

    private final BonEntreeCamionService bonEntreeCamionService;

    @GetMapping("liste")
    public ResponseEntity<HttpResponse> list() {
        return successResponse("Liste des camions déclarés", OK, bonEntreeCamionService.listDeclarationCamion());
    }

    @GetMapping("pagination/liste")
    public ResponseEntity<HttpResponse> list(@RequestParam int page, @RequestParam int size) {
        return successResponse("Liste des camions déclarés", OK, bonEntreeCamionService.listDeclarationCamion(size, page));
    }

    @PostMapping(value = "creation", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> creation(@RequestBody @Valid BonEntreeCamionRequest request) {
        return successResponse("Déclaration a été ajouter avec success", CREATED, bonEntreeCamionService.creationDeclaration(request));
    }

    @GetMapping("pesage/pagination/liste")
    public ResponseEntity<?> listPesage(@RequestParam("sensTrafic") String sensTrafic, @RequestParam int page, @RequestParam int size) {
        return successResponse("liste des camions attendus", OK, bonEntreeCamionService.listCamionsAttendus(sensTrafic, page, size));
    }

    @GetMapping("pesage/liste")
    public ResponseEntity<?> listPesage(@RequestParam("sensTrafic") String sensTrafic) {
        return successResponse("liste des camions attendus", OK, bonEntreeCamionService.listCamionsAttendus(sensTrafic));
    }

    @PutMapping(value = "{numBonEntreeCamion}/modification", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpResponse> updateVide(@PathVariable String numBonEntreeCamion, @RequestBody @Valid BonEntreeCamionUpdatePoidsRequest request) {
        return successResponse("le poids vide a été modifier avec success", CREATED, bonEntreeCamionService.updatePoidsVide(numBonEntreeCamion, request));
    }

    @GetMapping(value = "{numBonEntreeCamion}/redevance")
    public ResponseEntity<HttpResponse> redevance(@PathVariable String numBonEntreeCamion) {
        return successResponse("la redevance du bon d'entrée camion : " + numBonEntreeCamion, OK, bonEntreeCamionService.getRedevance(numBonEntreeCamion));
    }

    @GetMapping("{numBonEntre}/detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The entity exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<HttpResponse> searchBynumConteneur(@PathVariable String numBonEntre) {
        return successResponse("Information des marchandise transporter", OK, bonEntreeCamionService.listDetailBonEntreeCamion(numBonEntre));
    }
}
