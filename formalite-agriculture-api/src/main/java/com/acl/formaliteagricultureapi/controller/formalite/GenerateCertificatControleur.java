package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.services.formalite.GenerateCertificatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/report/certificat")
@Tag(name = "GenerateCertificatControleur",
        description = "Processus pour générer le jasper pour les certificats")
public class GenerateCertificatControleur {

    Logger logger= LoggerFactory.getLogger(GenerateCertificatControleur.class);

    private final GenerateCertificatService generateCertificatService;

    public GenerateCertificatControleur(GenerateCertificatService generateCertificatService) {
        this.generateCertificatService = generateCertificatService;
    }

    @CrossOrigin
    @GetMapping("cire/pdf")
    @Operation(summary = "Jasper Autorisation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<?> genereJasperAutorisation(
            @RequestParam("idFormalite") Long idFormalite) throws JRException, IOException {

        return generateCertificatService.generateCertificat(idFormalite);

    }

}
