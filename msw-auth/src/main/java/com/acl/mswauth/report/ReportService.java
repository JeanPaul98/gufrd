package com.acl.mswauth.report;


import com.acl.mswauth.dto.user.UserReportDto;
import com.acl.mswauth.dto.formalite.FormaliteReportDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportService {


    final DecimalFormat decimalFormat = new DecimalFormat("###,###.###");

    SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMMM yyyy");
    SimpleDateFormat sfDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sfDateHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");



    public InputStream exportUser(UserReportDto userReportDto, String format) throws IOException, JRException {

        List<Map<String, ?>> fields = new ArrayList();
        JRBeanCollectionDataSource jrDetailBeanCollection = new JRBeanCollectionDataSource(userReportDto.getDetailUsers());
        String filePath = "jasper/userStats.jrxml";

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("dateDebut","26-09-2024");
        params.put("total", userReportDto.getTotal());
        params.put("dateFin", sfDateHeure.format(userReportDto.getDateFin()));
        params.put("entreprise", userReportDto.getEntreprise());
        params.put("ligneMaritime", userReportDto.getLigneMaritime());
        params.put("transitaire", userReportDto.getTransitaire());
        params.put("consignataire", userReportDto.getConsignataire());
        params.put("operateurTerminaux", userReportDto.getOperateurTerminaux());
        params.put("logopal", "jasper/pal.png");
        params.put("chargeur", userReportDto.getChargeur());

        ReportManager reportManager = new ReportManager(params, userReportDto.getDetailUsers(),filePath);

        InputStream inputStream = null;
        switch (format.toUpperCase()) {
            case "PDF" :
                System.out.println("PDF");
                inputStream = reportManager.exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.PDF);
                break;
            case "DOCX" :
                inputStream = reportManager.exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.DOCX);
                break;
        }

        return  inputStream;
    }


    public InputStream exportFormalitePhytoTraiter(FormaliteReportDto reportDto, String format) throws IOException, JRException {

        List<Map<String, ?>> fields = new ArrayList();
        JRBeanCollectionDataSource jrDetailBeanCollection = new JRBeanCollectionDataSource(reportDto.getFormaliteStatistiqueDtos());
        String filePath = "jasper/statFormalite.jrxml";

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("periode",sfDateHeure.format(new Date()));
        params.put("total", reportDto.getTotal());
        params.put("declarant", reportDto.getDeclarant());
        params.put("logopal", "jasper/pal.png");

        ReportManager reportManager = new ReportManager(params, reportDto.getFormaliteStatistiqueDtos(),filePath);

        InputStream inputStream = null;
        switch (format.toUpperCase()) {
            case "PDF" :
                System.out.println("PDF");
                inputStream = reportManager.exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.PDF);
                break;
            case "DOCX" :
                inputStream = reportManager.exportFromJREmptyDataSourceToInputStream(ReportManager.ReportFormat.DOCX);
                break;
        }

        return  inputStream;
    }


}
