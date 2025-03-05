package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.controller.formalite.FormaliteController;
import com.acl.formaliteagricultureapi.controller.formalite.report.ReportService;
import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.atd.QrCodeDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenereAutorisationDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenererProcessVerbalDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.DetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.UpdateDetPvProduitDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.UpdateProcesVerbalDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ReportDetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.*;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.repository.DetPvProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.ProcesVerbalRepository;
import com.acl.formaliteagricultureapi.services.formalite.GenererProcessVerbalService;
import com.acl.formaliteagricultureapi.services.qrcode.QrcodeService;
import com.acl.formaliteagricultureapi.services.report.ReportProcessVerbalService;
import fr.opensagres.xdocreport.core.io.IOUtils;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 28/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class GenererProcesVerbalServiceImpl implements GenererProcessVerbalService {

    private final ProcesVerbalRepository procesVerbalRepository;

    private final FormaliteRepository formaliteRepository;

    private final ReportService reportService;

    private final ReportProcessVerbalService reportProcessVerbalService;

    private final DetPvProduitRepository detPvProduitRepository;

    Logger logger = LoggerFactory.getLogger(GenererProcesVerbalServiceImpl.class);
    private final QrcodeService qrcodeService;


    @Autowired
    private ProduitConverter produitConverter;

    public GenererProcesVerbalServiceImpl(ProcesVerbalRepository procesVerbalRepository, FormaliteRepository formaliteRepository, ReportService reportService, ReportProcessVerbalService reportProcessVerbalService, DetPvProduitRepository detPvProduitRepository, QrcodeService qrcodeService) {
        this.procesVerbalRepository = procesVerbalRepository;
        this.formaliteRepository = formaliteRepository;
        this.reportService = reportService;
        this.reportProcessVerbalService = reportProcessVerbalService;
        this.detPvProduitRepository = detPvProduitRepository;
        this.qrcodeService = qrcodeService;
    }


    @Override
    public ResponseEntity<byte[]> genererProcessVerbalNavire(Long idProcessVerbal) throws JRException, IOException {

        String filePath = "jasper/ship_pv_ip2.jrxml";

        HttpHeaders headers = new HttpHeaders();

        Optional<ProcesVerbal> data= procesVerbalRepository.findByFormalite(idProcessVerbal);
        logger.info("Process verbal ajout , {}, {}", idProcessVerbal, data.isPresent());

        if(data.isPresent()){

        logger.info("Process verbal , {}", idProcessVerbal);
            GenererProcessVerbalDto requestDto= new GenererProcessVerbalDto();
            requestDto.setVia(data.get().getVia());
            requestDto.setCommandant(data.get().getNomCommandant());
            requestDto.setDateInspection(data.get().getDateInspection());
            requestDto.setLieuInspection(data.get().getLieuInspection());
            requestDto.setOfficierNavire(data.get().getOfficierNavirePresent());
            requestDto.setPartieNavireVisitee(data.get().getPartieNavireVisitee());
            requestDto.setRemarque(data.get().getRemarque());
            requestDto.setAgentPV(data.get().getAgentPV());

            List<DetProcesVerbalProduit> detProcesVerbalProduits = detPvProduitRepository.
                    findByProcesVerbal(data.get().getId());

            List<UpdateDetPvProduitDto> detPvProduitDtoList = new ArrayList<>();

            detProcesVerbalProduits.forEach(dp-> {
                UpdateDetPvProduitDto detPvProduitDto = new UpdateDetPvProduitDto();
                    detPvProduitDto.setDescriptionEnvoie(dp.getDescriptionEnvoi());
                    detPvProduitDto.setOrigine(dp.getOrigine());
                    detPvProduitDto.setQuantite(dp.getQuantite());
                    detPvProduitDto.setMesure(dp.getMesure());
                    detPvProduitDtoList.add(detPvProduitDto);
            });
            requestDto.setDetPvProduitDtoList(detPvProduitDtoList);
            requestDto.setQrCode(genereQrCode(data.get().getNomAgent(),
                    data.get().getNumero()));
            InputStream in= reportProcessVerbalService.reportProcessConfig(requestDto,filePath,
                    "PDF",data.get());

            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(media, headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public Formalite updateFormalite(Formalite formalite) {
        formalite.setEtat(Etat.TRAITER);
        formalite.setDateTraitement(new Date());
        formalite.setUpdateAt(new Date());
        return formaliteRepository.save(formalite);
    }

    private String genereQrCode(String nomImportateur, String numeroDemande) throws IOException {
        QrCodeDto qrCodeDto = new QrCodeDto();
        qrCodeDto.setTypeDocument("PHYTOSANITAIRE CARGAISON");
        qrCodeDto.setRefDocument(numeroDemande);
        qrCodeDto.setNif("NIF234899");
        qrCodeDto.setExp("2024-10-31");
        qrCodeDto.setSlug("pal_autorisation_depotage");
        qrCodeDto.setCat("CAT");
        qrCodeDto.setNomSociete(nomImportateur);
        qrCodeDto.setAutoriteCompetente("PROTECTION DES VEGETAUX");
        qrCodeDto.setModeUtilisation("UNIQUE");

        return qrcodeService.generateQrCode(qrCodeDto);
    }


}
