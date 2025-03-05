package com.acl.mswauth.controller.jasper;

import com.acl.mswauth.dto.formalite.FormaliteStatistiqueDto;
import com.acl.mswauth.service.report.ReportUserService;
import com.acl.mswauth.service.statistique.StatistiqueFormaliteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@RestController
@RequestMapping("/api/formalite")
public class FormaliteTraiterJasperController {

    private final ReportUserService reportUserService;

    public FormaliteTraiterJasperController(ReportUserService reportUserService) {
        this.reportUserService = reportUserService;
    }


    @GetMapping("phyto/pdf")
    public ResponseEntity<?> generateReport() throws IOException {
        return reportUserService.getFormalitePhytoReport();
    }

}
