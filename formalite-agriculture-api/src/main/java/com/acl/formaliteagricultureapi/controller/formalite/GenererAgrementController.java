package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.services.agrement.GenererAgrementService;
import com.acl.formaliteagricultureapi.services.formalite.GenererProcessVerbalService;
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
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@RestController
@RequestMapping("api/v1/report/agrement")
@Tag(name = "GenererAgrementController",
        description = "Processus pour générer le jasper des agréments")
public class GenererAgrementController {

    Logger logger= LoggerFactory.getLogger(GenererAgrementController.class);

    private final GenererAgrementService genererAgrementService;

    public GenererAgrementController(GenererAgrementService genererAgrementService) {
        this.genererAgrementService = genererAgrementService;
    }


    @CrossOrigin
    @GetMapping("pdf")
    @Operation(summary = "Jasper Process Verbal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<byte[]> genereJasperProcessVerbal(
            @RequestParam("code") String codeAgrement) throws JRException, IOException {

        return genererAgrementService.genererAgrement(codeAgrement);

    }

}
