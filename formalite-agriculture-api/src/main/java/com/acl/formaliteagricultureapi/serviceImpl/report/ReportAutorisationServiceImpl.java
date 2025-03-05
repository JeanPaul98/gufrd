package com.acl.formaliteagricultureapi.serviceImpl.report;

import com.acl.formaliteagricultureapi.dto.autorisation.LaisserPasserDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenereAutorisationDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.LaisserPasser;
import com.acl.formaliteagricultureapi.repository.LaisserPasserRepository;
import com.acl.formaliteagricultureapi.services.report.ReportAutorisation;
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
import java.util.Optional;

/**
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class ReportAutorisationServiceImpl implements ReportAutorisation {

    Logger logger = LoggerFactory.getLogger(ReportAutorisationServiceImpl.class);

    private final LaisserPasserRepository laisserPasserRepository;

    @Autowired
    private Environment env;

    @Autowired
    private ReportManager reportManager;

    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

    public ReportAutorisationServiceImpl(LaisserPasserRepository laisserPasserRepository) {
        this.laisserPasserRepository = laisserPasserRepository;
    }


    @Override
    public InputStream reportAutorisationConfig(GenereAutorisationDto genereAutorisationDto,
                                                String filePath, String format, Formalite formalite)
            throws JRException, IOException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        //filePath = "reports/docs/autorisationReporting.jrxml";
        params.put("nomImportateur", genereAutorisationDto.getNomImportateur());
        params.put("typeAutorisation", formalite.getAutorisation().getTypeAutorisation().
                getLibelle());
        params.put("numeroDemande", formalite.getNumGenere());
        params.put("dateTraitement", format1.format(formalite.getDateSoumise()));
        params.put("logo","docs/logo.png");
        params.put("qrCode",genereAutorisationDto.getQrCode());
        params.put("arreteMinistere", formalite.getAutorisation().getTypeAutorisation().getArreteMinistere());
        ReportManager reportManager = new ReportManager(params,
                genereAutorisationDto.getDetAutorisationProduitDtos(), filePath);

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

    @Override
    public InputStream reportAutorisationLaisserPasser(LaisserPasserDto laisserPasserDto, String filePath, String format) throws JRException, IOException {

        Optional<LaisserPasser>optionalLaisserPasser=laisserPasserRepository.
                findById(laisserPasserDto.getIdInspecteur());

        HashMap<String, Object> params = new HashMap<>();

        params.put("numLaisserPasser", laisserPasserDto.getNumeroLaisserPasser());
        params.put("nomInspecteur", optionalLaisserPasser.get().getInspecteur().getNomInspecteur());
        params.put("dateInspection", format1.format(laisserPasserDto.getDateInspection()));
        params.put("provenance", laisserPasserDto.getProvenance());
        params.put("abattoir", laisserPasserDto.getAbattoir());
        params.put("numReferenceCertif", laisserPasserDto.getReferenceCertificat());
        params.put("numReferenceCont", laisserPasserDto.getReferenceConteneur());
        params.put("numAutoDepotage", laisserPasserDto.getNumeroAutorisationDepotage());
        params.put("dateDepotage", format1.format(laisserPasserDto.getDateAutorisationDepotage()));
        params.put("nomImportateur", laisserPasserDto.getNomImportateur());
        params.put("moyenTransport", laisserPasserDto.getMoyenTransport());
        params.put("typeAutorisation", optionalLaisserPasser.get().getFormalite().
                getAutorisation().getTypeAutorisation().getLibelle());
        params.put("numeroDemande", optionalLaisserPasser.get().getFormalite().getNumGenere());
        params.put("DateTraitement", format1.format(optionalLaisserPasser.get().getCreatedAt()));

        ReportManager reportManager = new ReportManager(params, filePath);

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