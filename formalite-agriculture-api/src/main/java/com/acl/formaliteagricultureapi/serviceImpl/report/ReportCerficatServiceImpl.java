package com.acl.formaliteagricultureapi.serviceImpl.report;

import com.acl.formaliteagricultureapi.dto.formalite.GenerateCertificatDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.services.report.ReportCertificatService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Service
public class ReportCerficatServiceImpl implements ReportCertificatService {

    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

    Logger logger= LoggerFactory.getLogger(ReportCerficatServiceImpl.class);

    @Override
    public InputStream reportProcessConfig(GenerateCertificatDto generateCertificatDto,
                                           String filePath, String format, Formalite formalite) throws JRException, IOException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        //filePath = "reports/docs/autorisationReporting.jrxml";
        logger.info("produits {} ", generateCertificatDto.getDetProduits().toString());
        params.put("natureProduit", generateCertificatDto.getNatureProduit());
        params.put("paysOrigine", generateCertificatDto.getPaysOrigine());
        params.put("ref", formalite.getCertificat().getAutre());
        params.put("numeroDemande", formalite.getNumGenere());
        params.put("dateTraitement", format1.format(formalite.getDateAccepte()));
        params.put("logo","jasper/logo.png");

        ReportManager reportManager = new ReportManager(params,generateCertificatDto.getDetProduits(), filePath);

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
