package com.acl.formaliteagricultureapi.controller.imports.phytosanitaire;

import com.acl.formaliteagricultureapi.controller.imports.autorisation.AutorisationAlimentAnimauxClientControlleur;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspPhytoNavireClientCrudService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspectionPhtyoNavireManyService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire.InspectionPhytoNavireUpService;
import com.acl.formaliteagricultureapi.validator.RetrieveValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/phytosanitaire/navire")
@Tag(name = "InspectionPhytoNavireController",
        description = "Processus de formalité d'inspection phytosanitaire de navire")
public class InspectionPhytoNavireController {

    private final InspPhytoNavireClientCrudService inspPhytoNavireClientService;

    private final InspectionPhytoNavireUpService inspectionPhytoNavireUpService;

    private final InspectionPhtyoNavireManyService inspectionPhtyoNavireManyService;

    private final FormaliteService formaliteService;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientControlleur.class);

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public InspectionPhytoNavireController(InspPhytoNavireClientCrudService inspPhytoNavireClientService, InspectionPhytoNavireUpService inspectionPhytoNavireUpService,
                                           InspectionPhtyoNavireManyService inspectionPhtyoNavireManyService,
                                           FormaliteService formaliteService) {
        this.inspPhytoNavireClientService = inspPhytoNavireClientService;
        this.inspectionPhytoNavireUpService = inspectionPhytoNavireUpService;
        this.inspectionPhtyoNavireManyService = inspectionPhtyoNavireManyService;
        this.formaliteService=formaliteService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insert(
            @Valid @RequestBody PhytosanitaireNavireClientDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return inspPhytoNavireClientService.create(request);

    }

    @CrossOrigin
    @GetMapping("nonsoumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumise() {
        logger.info("Liste non soumise envoie ");
        return inspectionPhtyoNavireManyService.listAutorisationEnlevement(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.navire"));

    }

    @CrossOrigin
    @GetMapping("nonsoumis/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listNonSoumiseClient(@RequestParam String client) {
        logger.info("Liste non soumise envoie ");
        return inspectionPhtyoNavireManyService.listPhytosanitaireByCompte(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.navire"),client);

    }


    @CrossOrigin
    @GetMapping("/soumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return inspectionPhtyoNavireManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.navire"));

    }

    @CrossOrigin
    @GetMapping("soumis/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis(@RequestParam String client) {

        return inspectionPhtyoNavireManyService.listPhytosanitaireByCompte(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.navire"), client);

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return inspectionPhtyoNavireManyService.listAutorisationEnlevement(Etat.REJETER,
                env.getProperty("message.type.phytosanitaire.ref.navire"));

    }

    @CrossOrigin
    @GetMapping("rejetee/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter(@RequestParam String client) {

        return inspectionPhtyoNavireManyService.listPhytosanitaireByCompte(Etat.REJETER,
                env.getProperty("message.type.phytosanitaire.ref.navire"), client);

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return inspectionPhtyoNavireManyService.listAutorisationEnlevement(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.navire"));

    }

    @CrossOrigin
    @GetMapping("accepter/client")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter(@RequestParam String client) {

        return inspectionPhtyoNavireManyService.listPhytosanitaireByCompte(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.navire"), client);

    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return inspectionPhtyoNavireManyService.listStatDemande();

    }


    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {
        logger.info("Message ,{}", env.getProperty("message.type.phytosanitaire.ref.navire"));

        return inspectionPhtyoNavireManyService.listAutorisationEnlevement(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.navire"));

    }

    @CrossOrigin
    @GetMapping("traiter/client")
    @Operation(summary = "liste  les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter(@RequestParam String client) {
        logger.info("Message ,{}", env.getProperty("message.type.phytosanitaire.ref.navire"));

        return inspectionPhtyoNavireManyService.listPhytosanitaireByCompte(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.navire"), client);

    }

    @CrossOrigin
    @PostMapping("updateDemande")
    @Operation(summary = "Soumission de sa demande inspection navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> soumettre(
            @Valid @RequestBody PhytosanitaireNavireClientListDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return inspectionPhytoNavireUpService.updateDemande(request);

    }

}
