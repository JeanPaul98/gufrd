package com.acl.mswauth.controller;

import com.acl.mswauth.dto.register.RegisterDto;
import com.acl.mswauth.dto.register.RegisterStructureDto;
import com.acl.mswauth.dto.register.RegisterUpdateDto;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.service.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.acl.mswauth.request.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.security.SecureRandom;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserStatisitiqueService userStatisitiqueService;

    private final UserSearchService userSearchService;

    private final UserUpdateService userUpdateService;

    private final UserListService userListService;

    private final UserStructureService userStructureService;
	
	@Autowired
	private UserService userService;

    private final UserClientService userClientService;

    public UserController(UserStatisitiqueService userStatisitiqueService, UserSearchService userSearchService, UserUpdateService userUpdateService, UserListService userListService, UserStructureService userStructureService, UserClientService userClientService) {
        this.userStatisitiqueService = userStatisitiqueService;
        this.userSearchService = userSearchService;
        this.userUpdateService = userUpdateService;
        this.userListService = userListService;
        this.userStructureService = userStructureService;
        this.userClientService = userClientService;
    }

    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("register")
    @Operation(summary = "Webservice  pour créer un utilisateur", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterStructureDto request) {
        logger.info("createUser: {}", request);
         return userStructureService.insert(request);
    }

    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("register/client")
    @Operation(summary = "Webservice  pour créer un utilisateur", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createMutlipleLogin(@Valid @RequestBody RegisterDto request) {
        logger.info("Create user, {} ", request);
            return userClientService.insert(request);
    }



    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("register/structure")
    @Operation(summary = "Webservice  pour créer un utilisateur d'une structure",
            description = "Il faut que la structure existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> createUserStructure(@Valid @RequestBody RegisterStructureDto request) {
        logger.info("Create user, {} ", request);
        return userStructureService.insert(request);
    }


    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("register/update")
    @Operation(summary = "Webservice  pour faire la mise a jour utilisateur", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateUser(@Valid @RequestBody RegisterUpdateDto request) {
        logger.info("Create user, {} ", request);

        return userUpdateService.update(request);
    }

    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("register/profil")
    @Operation(summary = "Webservice  pour faire la mise a jour utilisateur", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateUserProfil(@Valid @RequestBody RegisterUpdateDto request) {
        logger.info("Create user, {} ", request);

        return userUpdateService.updateProfil(request);
    }
    
    /**
     * Création d'un user
     * @param request
     * @return
     */
    @CrossOrigin
    @PutMapping("id/{id}")
    @Operation(summary = "Webservice  pour modifier un utilisateur", description = "Il faut que le client existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,  @Valid @RequestBody RegisterRequest request) {
        logger.info("Create user " + request);
        if(request.getStructureId()!=null){
        	return userService.storeStructureUser(request);
        }
        return userService.storeUser(request);
    }
    
    
    @CrossOrigin
    @GetMapping("")
    @Operation(summary = "Web service pour lister tous les utilisateurs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUsers() {
        return userService.findAll();
    }

    @CrossOrigin
    @GetMapping("statistique")
    @Operation(summary = "Web service pour lister tous les utilisateurs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllStatisques() {
        return userStatisitiqueService.getUserStatisitique();
    }


    @CrossOrigin
    @GetMapping("search")
    @Operation(summary = "Web service pour rechercher un utilisateur",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> searchUsersKeyword(@RequestParam String keyword, int page, int size) {
        return userSearchService.getSearchResult(keyword, page,size);
    }


    @CrossOrigin
    @GetMapping("detail")
    @Operation(summary = "Web service  qui affiche le détail d'un utilisateur",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getDetailUser(@RequestParam Long request) {
        return userListService.findByIdUser(request);
    }
    
    @CrossOrigin
    @GetMapping("structure/{id}")
    @Operation(summary = "Uri  qui liste les utilisateurs d'une structure",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUsersByStructureId(@PathVariable("id") Long structureId) {
        return userService.findByStructure(structureId);
    }
    
    @CrossOrigin
    @GetMapping("status/{status}")
    @Operation(summary = "Web  qui liste les utilisateurs selon le status",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUsersByStructureId(@PathVariable("status") boolean status) {
        return userService.findByStatus(status);
    }

    @CrossOrigin
    @GetMapping("status/{status}/pageable")
    @Operation(summary = "Web  qui liste les utilisateurs selon le status",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllUsersByPageable(@PathVariable("status") boolean status,
                                                   @RequestParam int page, @RequestParam int size) {
        return userService.findByStatusPageable(status, page,size);
    }


    @CrossOrigin
    @GetMapping("actif")
    @Operation(summary = "Web  qui liste les utilisateurs actifs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAllActifs(@RequestParam int page, @RequestParam int size) {
        return userListService.getAllUserActiveList(page,size);
    }


    @CrossOrigin
    @GetMapping("all")
    @Operation(summary = "Web  qui liste les utilisateurs actifs",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> getAll() {
        return userListService.getAllActiveList();
    }



    /**
     * Active ou desactive un utilisateur
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("active/{user}")
    @Operation(summary = "Web  qui active ou desactive un utilisateur",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> actifUser(@PathVariable("user") Long id) {
        return userService.actifUser(id);
    }


    /**
     * Active ou desactive un utilisateur
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("valide")
    @Operation(summary = "Web  qui liste les utilisateurs valide ou non",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> valideUser(@RequestParam boolean etat) {
        logger.info("Valide ,{} ", etat);
        return userService.findValideUser(etat);
    }


    /**
     * Détermine l'utilisateur connecter
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("currentuser")
    @Operation(summary = "Utilisateur connecté",
            description = "Les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.findCurrentUser(currentUserName);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);

    }

    /**
     * Détermine l'utilisateur connecter
     * @param
     * @return
     */
    @CrossOrigin
    @GetMapping("profil")
    @Operation(summary = "Profil de l'utilisateur connecté",
            description = "Profil de l'utilisateur connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> profilUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userListService.findByProfil(currentUserName);
        }
        
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("profil/email")
    @Operation(summary = "Profil de l'utilisateur connecté",
            description = "Profil de l'utilisateur connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> profilUserByEmail(@RequestParam String email) {

            return userListService.findByProfil(email);

    }


    @CrossOrigin
    @GetMapping("delete/{id}")
    @Operation(summary = "delete l'utilisateur",
            description = "Suprime l'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        logger.info("Delete user " + id);
        return  userService.deleteUser(id);
    }


    @CrossOrigin
    @GetMapping("delete/logout")
    @Operation(summary = "delete l'utilisateur",
            description = "Suprime l'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Data not found"),
            @ApiResponse(responseCode = "403", description = "FORBIDEN: Data not exist"),
            @ApiResponse(responseCode = "200", description = "OK: the operation is successfully ")})
    public ResponseEntity<?> logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.deleteConnectedUser(currentUserName);
        }
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), "Utilisateur n'existe pas"), HttpStatus.OK);

    }


}
