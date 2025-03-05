package com.acl.mswauth.controller.jasper;

import com.acl.mswauth.service.report.ReportUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@RestController
@RequestMapping("/api/jasper")
public class UserJasperController {

    private final ReportUserService reportUserService;


    public UserJasperController(ReportUserService reportUserService) {
        this.reportUserService = reportUserService;
    }

    @GetMapping("pdf")
    public ResponseEntity<?> generateReport() {
        return reportUserService.getUserReport();
    }

}
