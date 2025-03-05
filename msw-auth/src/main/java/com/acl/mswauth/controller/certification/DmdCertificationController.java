package com.acl.mswauth.controller.certification;

import com.acl.mswauth.controller.AuthentificationController;
import com.acl.mswauth.dto.ClientDto;
import com.acl.mswauth.dto.certification.DmdCertificationDto;
import com.acl.mswauth.dto.certification.VerifyDto;
import com.acl.mswauth.service.certification.DdmCertificationListService;
import com.acl.mswauth.service.certification.DmdCertificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@RestController
@RequestMapping("/api/certification")
@Tag(name = "DmdCertificationController",
        description = "Processus de création de groupe")
public class DmdCertificationController {

    private final Logger logger = LoggerFactory.getLogger(DmdCertificationController.class);

    private final DmdCertificationService dmdCertificationService;

    private final DdmCertificationListService dmdCertificationListService;

    public DmdCertificationController(DmdCertificationService dmdCertificationService, DdmCertificationListService dmdCertificationListService) {
        this.dmdCertificationService = dmdCertificationService;
        this.dmdCertificationListService = dmdCertificationListService;
    }

    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Webservice  qui crée le client", description = "Demande de certification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createClient(@Valid @RequestBody DmdCertificationDto request) {
        logger.info("Creation request {}  ", request);
        return dmdCertificationService.insert(request);
    }

    @CrossOrigin
    @PostMapping("verify")
    @Operation(summary = "Webservice  qui vérifie le code de la demande", description = "Demande de certification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> verify(@Valid @RequestBody VerifyDto request) {
        logger.info("Creation request {}  ", request);
        return dmdCertificationService.verify(request);
    }

    @CrossOrigin
    @GetMapping("certifier")
    @Operation(summary = "Webservice  qui crée le client", description = "Demande de certification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> allCertifier(@RequestParam int page, @RequestParam int size) {

        return dmdCertificationListService.getAllCertificationList(page,size);
    }
}
