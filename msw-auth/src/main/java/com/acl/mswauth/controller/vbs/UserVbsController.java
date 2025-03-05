package com.acl.mswauth.controller.vbs;

import com.acl.mswauth.service.user.UserListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @author kol on 11/26/24
 * @project msw-auth
 */
@RestController
@RequestMapping(value = "/api/vbs", produces = {APPLICATION_JSON_VALUE})
@Slf4j
public class UserVbsController {

    private final UserListService userListService;

    public UserVbsController(UserListService userListService) {
        this.userListService = userListService;
    }

    @CrossOrigin
    @GetMapping("profil")
    @Operation(summary = "Profil de l'utilisateur connecté",
            description = "Profil de l'utilisateur connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> profilUserByEmail(@RequestParam String email) {

        log.info("profilUserByEmail : {}", email);

        return userListService.findByProfil(email);

    }

}
