package com.acl.formaliteagricultureapi.serviceImpl.report;

import com.acl.formaliteagricultureapi.dto.formalite.GenererProcessVerbalDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import com.acl.formaliteagricultureapi.repository.LaisserPasserRepository;
import com.acl.formaliteagricultureapi.services.report.ReportProcessVerbalService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class ReportProcessVerbalServiceImpl implements ReportProcessVerbalService {

    Logger logger = LoggerFactory.getLogger(ReportProcessVerbalServiceImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private ReportManager reportManager;

    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public InputStream reportProcessConfig(GenererProcessVerbalDto genererProcessVerbalDto, String filePath, String format,
                                           ProcesVerbal procesVerbal) throws JRException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("ref", procesVerbal.getNumero());
        params.put("dateSignature", procesVerbal.getDatePv());
        params.put("dateArrivee", procesVerbal.getDateArrivee());
        params.put("dateInspection", genererProcessVerbalDto.getDateInspection());
        params.put("commandant", genererProcessVerbalDto.getCommandant());
        params.put("officier", procesVerbal.getOfficierNavirePresent());
        params.put("partieVisite", procesVerbal.getPartieNavireVisitee());
        params.put("provenance", procesVerbal.getProvenance());
        params.put("affreteur", procesVerbal.getFormalite().getAffreteur());
        params.put("nomNavire", procesVerbal.getFormalite().getNomNavire());
        params.put("via", procesVerbal.getVia());
        params.put("logo","jasper/logo.png");
        params.put("remarques",procesVerbal.getRemarque());
        params.put("codeQr", genererProcessVerbalDto.getQrCode());
        params.put("provisions", "AUCUNE");
        params.put("chefCook", procesVerbal.getAgentPV());
        ReportManager reportManager = new ReportManager(params,
                genererProcessVerbalDto.getDetPvProduitDtoList(), filePath);
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
