package com.acl.mswauth.controller;

import com.acl.mswauth.playload.JwtAuthenticationResponse;
import com.acl.mswauth.request.PaysRequest;
import com.acl.mswauth.service.PaysServices;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pays")
/*
@Api(tags = {"PAYS"}, value = "Création de pays")
*/
public class PaysController {

    private final Logger logger = LoggerFactory.getLogger(PaysController.class);

    @Autowired
    private PaysServices paysServices;

    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @PostMapping("")
  /*  @ApiOperation(value = "Webservice creation pays", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
*/    public ResponseEntity<?> createPays(@Valid @RequestBody PaysRequest paysRequest) {
        logger.info("Creation pays " + paysRequest);
        return paysServices.create(paysRequest);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("")
   /* @ApiOperation(value = "Webservice liste des pays", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "NOT_FOUND: Data not found"),
            @ApiResponse(code = 403, message = "FORBIDEN: Data not exist"),
            @ApiResponse(code = 200, message = "OK: the operation is successfully ")})
  */  public ResponseEntity<?> getPays() {
        return paysServices.getAllPays();
    }


}
