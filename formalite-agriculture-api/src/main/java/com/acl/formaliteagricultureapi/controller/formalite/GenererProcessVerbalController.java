package com.acl.formaliteagricultureapi.controller.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.services.formalite.GenereAutorisationService;
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
@RequestMapping("api/v1/report/processVerbal")
@Tag(name = "GenererProcessVerbalController",
        description = "Processus pour générer le jasper des processVerbale")
public class GenererProcessVerbalController {

    Logger logger= LoggerFactory.getLogger(GenererProcessVerbalController.class);

    private final GenererProcessVerbalService genererProcessVerbalService;

    public GenererProcessVerbalController(GenererProcessVerbalService genererProcessVerbalService) {
        this.genererProcessVerbalService = genererProcessVerbalService;
    }

    @CrossOrigin
    @GetMapping("pv/pdf")
    @Operation(summary = "Jasper Process Verbal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: The object is not exist into database"),
            @ApiResponse(responseCode = "200", description = "OK: Transaction  is OK ")})
    public ResponseEntity<byte[]> genereJasperProcessVerbal(
            @RequestParam("idFormalite") Long idProcessVerbal) throws JRException, IOException {

        return genererProcessVerbalService.genererProcessVerbalNavire(idProcessVerbal);

    }

}
