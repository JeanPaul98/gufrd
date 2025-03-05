package com.acl.formaliteagricultureapi.controller.imports.phytosanitaire;

import com.acl.formaliteagricultureapi.controller.imports.autorisation.AutorisationAlimentAnimauxClientControlleur;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientListDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.pharmaceutique.InspectionPhtyoPharmaManyService;
import com.acl.formaliteagricultureapi.services.phytosanitaire.importation.pharmaceutique.InspectionPhytoPharmaClientCrudService;
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
 * @author kol on 24/08/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/phytosanitaire/pharmaceutique")
@Tag(name = "InspectionPhytoPharmaControlleur",
        description = "Processus de formalité d'inspection phytosanitaire produits pharmaceutiques")
public class InspectionPhytoPharmaControlleur {


    private final InspectionPhytoPharmaClientCrudService inspectionPhytoPharmaClientCrudService;

    private final InspectionPhtyoPharmaManyService inspectionPhtyoPharmaManyService;

    private final FormaliteService formaliteService;

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(AutorisationAlimentAnimauxClientControlleur.class);

    @Autowired
    private RetrieveValidationError retrieveValidationError;

    public InspectionPhytoPharmaControlleur(InspectionPhytoPharmaClientCrudService
                                                    inspectionPhytoPharmaClientCrudService,
                                            InspectionPhtyoPharmaManyService
                                                    inspectionPhtyoPharmaManyService,
                                            FormaliteService formaliteService) {
        this.inspectionPhytoPharmaClientCrudService = inspectionPhytoPharmaClientCrudService;
        this.inspectionPhtyoPharmaManyService = inspectionPhtyoPharmaManyService;
        this.formaliteService=formaliteService;
    }


    @CrossOrigin
    @PostMapping("insert")
    @Operation(summary = "Insérer les données pour l'inspection phytosanitaire de navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> insertAutorisationEnlevement(
            @Valid @RequestBody PhytosanitaireProduitPhytopharmaClientDto request,
            HttpServletRequest httprequest, BindingResult result) {
        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        logger.info("PHytosanitaire insert phyto pharma, {}", request);
        return inspectionPhytoPharmaClientCrudService.create(request);

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
        return inspectionPhtyoPharmaManyService.listAutorisationEnlevement(Etat.NON_SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.pharma"));

    }


    @CrossOrigin
    @GetMapping("soumis")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listSoumis() {

        return inspectionPhtyoPharmaManyService.listAutorisationEnlevement(Etat.SOUMIS,
                env.getProperty("message.type.phytosanitaire.ref.pharma"));

    }

    @CrossOrigin
    @GetMapping("rejetee")
    @Operation(summary = "Liste les données pour phytosanitaire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listRejeter() {

        return inspectionPhtyoPharmaManyService.listAutorisationEnlevement(Etat.REJETER,
                env.getProperty("message.type.phytosanitaire.ref.pharma"));

    }

    @CrossOrigin
    @GetMapping("accepter")
    @Operation(summary = "Liste les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listAccepter() {

        return inspectionPhtyoPharmaManyService.listAutorisationEnlevement(Etat.ACCEPTER,
                env.getProperty("message.type.phytosanitaire.ref.pharma"));

    }

    @CrossOrigin
    @GetMapping("traiter")
    @Operation(summary = "liste  les données pour l'autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict: The product exist into database"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listTraiter() {

        return inspectionPhtyoPharmaManyService.listAutorisationEnlevement(Etat.TRAITER,
                env.getProperty("message.type.phytosanitaire.ref.pharma"));

    }

    @CrossOrigin
    @PostMapping("updateDemande")
    @Operation(summary = "Soumission de sa demande inspection navire")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> soumettre(
            @Valid @RequestBody PhytosanitaireProduitPhytopharmaClientListDto request,
            HttpServletRequest httprequest, BindingResult result) throws Exception {

        // gestion de la validation
        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return inspectionPhytoPharmaClientCrudService.validerDemande(request);

    }

    @CrossOrigin
    @PostMapping("accepterdemande")
    @Operation(summary = "Accepter la liste des demandes soumises de l'autorisation de l'envelement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> accepterDemande(@Valid @RequestBody UpdateFormaliteDto request,
                                             HttpServletRequest httprequest, BindingResult result)  {

        if (result.hasErrors()) {
            return retrieveValidationError.retrieveErrors(result);
        }
        return formaliteService.accepterDemandeAutorisation(request);


    }

    @CrossOrigin
    @GetMapping("statistiqueDemande")
    @Operation(summary = "Liste des demandes soumises non soumises , rejeter acepter , et valider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> listeDemande()  {

        return inspectionPhtyoPharmaManyService.listStatDemande();

    }
}
