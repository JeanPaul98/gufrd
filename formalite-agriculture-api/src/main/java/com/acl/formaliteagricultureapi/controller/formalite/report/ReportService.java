package com.acl.formaliteagricultureapi.controller.formalite.report;


import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import com.acl.formaliteagricultureapi.serviceImpl.report.ReportManager;
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



    public InputStream exportProcesVerbal(List<DetProcesVerbalProduit> detProcesVerbalProduits, String format) throws IOException, JRException {

        List<Map<String, ?>> fields = new ArrayList();
        JRBeanCollectionDataSource jrDetailBeanCollection = new JRBeanCollectionDataSource(detProcesVerbalProduits);
        String filePath = "docs/pv_navire_phyto.jrxml";


            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("numClient","11831-A");
            params.put("province", "KINSHASA");
            params.put("operateur", "AIRTEL");
            params.put("nomPrenom","MWAMBA KABAMBA");
            params.put("reference", "MP231126.1819.C10702");
            params.put("montantPayer", "266.0 CDF" );
            params.put("logo","reports/docs/images/img.png");
            params.put("soldeCompte","89 000 CDF");
            params.put("datePaiement", "26-11-2023 18:20");


        ReportManager reportManager = new ReportManager(params, detProcesVerbalProduits,filePath);

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
