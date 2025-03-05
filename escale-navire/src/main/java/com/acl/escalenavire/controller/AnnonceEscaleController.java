package com.acl.escalenavire.controller;


import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.services.AnnonceEscaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/escale")
public class AnnonceEscaleController {

    private final AnnonceEscaleService annonceEscaleService;

    @CrossOrigin
    @GetMapping("/list")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> listeAnnonce(@RequestParam int page,@RequestParam int size) {
        return annonceEscaleService.list(page,size);
    }

    @CrossOrigin
    @GetMapping("/numeroAan")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getlistAnnByNumeroAan(@RequestParam String numeroAan) {
        return annonceEscaleService.listAnnByNumeroAan(numeroAan);
    }

    @CrossOrigin
    @GetMapping("/affreteurArrivee")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getlistAnnByAffect(@RequestParam String affreteurArrivee) {
        return annonceEscaleService.listAnnByAffect(affreteurArrivee);
    }

    @CrossOrigin
    @GetMapping("/port/{annonceEscaleId}")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getPortByAnnonceEscale(@PathVariable Long annonceEscaleId) {
        return annonceEscaleService.findPortByAnnonceEscale(annonceEscaleId);
    }


    @CrossOrigin
    @GetMapping("/navire/{id}")
    @ApiOperation(value = "liste des annonce escale", response = ApiResponseModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 200, message = "OK: liste des transactions ")})
    ResponseEntity<?> getNavireByAnnonceEscale(@PathVariable Long id) {
        return annonceEscaleService.findNavireByAnnonceEscale(id);
    }

}
