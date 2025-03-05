package com.acl.formaliteagricultureapi.serviceImpl.report;

import com.acl.formaliteagricultureapi.dto.agrement.GenererAgrementDto;
import com.acl.formaliteagricultureapi.services.report.ReportAgrementService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
public class ReportAgrementServiceImpl implements ReportAgrementService {

    @Autowired
    private Environment env;

    @Autowired
    private ReportManager reportManager;


    @Override
    public InputStream reportAgrementProcessConfig(GenererAgrementDto genererAgrementDto, String filePath, String format) throws JRException, IOException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("arrete", genererAgrementDto.getArrete());
        params.put("numeroDemande", genererAgrementDto.getNumeroDemande());

        ReportManager reportManager = new ReportManager(params,
                genererAgrementDto.getDetAgrementDtoList(), filePath);
        InputStream inputStream = null;


        switch (format.toUpperCase()) {
            case "PDF":
                System.out.println("PDF");
                inputStream = reportManager.
                        exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.PDF);
                break;
            case "DOCX":
                inputStream = reportManager.
                        exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.DOCX);
                break;
        }

        return inputStream;
    }
}
