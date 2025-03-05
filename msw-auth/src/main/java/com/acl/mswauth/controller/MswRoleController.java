package com.acl.mswauth.controller;

import com.acl.mswauth.service.PaysServices;
import com.acl.mswauth.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class MswRoleController {

    private final Logger logger = LoggerFactory.getLogger(MswRoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * Groupe des utilisateurs
     * @return
     */
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Liste les roles",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> listRole() {
        return roleService.getAllRole();
    }
}
