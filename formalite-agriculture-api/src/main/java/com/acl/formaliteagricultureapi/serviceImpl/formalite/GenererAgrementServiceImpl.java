package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.dto.agrement.DetAgrementDto;
import com.acl.formaliteagricultureapi.dto.agrement.GenererAgrementDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenererProcessVerbalDto;
import com.acl.formaliteagricultureapi.dto.procesVerbal.UpdateDetPvProduitDto;
import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import com.acl.formaliteagricultureapi.repository.AutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.repository.DemandeAutorisationAgrementRepository;
import com.acl.formaliteagricultureapi.services.agrement.GenererAgrementService;
import com.acl.formaliteagricultureapi.services.report.ReportAgrementService;
import fr.opensagres.xdocreport.core.io.IOUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
@Slf4j
public class GenererAgrementServiceImpl implements GenererAgrementService {

    private final AutorisationAgrementRepository autorisationAgrementRepository;

    private final ReportAgrementService reportAgrementService;

    private final DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository;

    public GenererAgrementServiceImpl(AutorisationAgrementRepository autorisationAgrementRepository, ReportAgrementService reportAgrementService, DemandeAutorisationAgrementRepository demandeAutorisationAgrementRepository) {
        this.autorisationAgrementRepository = autorisationAgrementRepository;
        this.reportAgrementService = reportAgrementService;
        this.demandeAutorisationAgrementRepository = demandeAutorisationAgrementRepository;
    }

    @Override
    public ResponseEntity<byte[]> genererAgrement(String code) throws JRException, IOException {
        String filePath = "jasper/agrement.jrxml";

        HttpHeaders headers = new HttpHeaders();

        Optional<AutorisationAgrement> data= autorisationAgrementRepository.findByCodeDmande(code);

        log.info("Mise a jour autorisation, {}",  data.isPresent());

        if(data.isPresent()){

            log.info("Agrement , {}", code);

            List<DetAgrementDto> detAgrementDtoList =  new ArrayList<>();
            DetAgrementDto detAgrementDto = new DetAgrementDto(data.get().getNumero(), data.get().getActivite(),
                    data.get().getDmdAutorisationAgrement().getSociete().getRaisonSociale(), data.get()
                    .getDmdAutorisationAgrement().getSociete().getAdresse());

            detAgrementDtoList.add(detAgrementDto);

            GenererAgrementDto genererAgrementDto = new GenererAgrementDto(data.get().getNumero(), "ARRETE",
                    detAgrementDtoList);

            InputStream in= reportAgrementService.reportAgrementProcessConfig(genererAgrementDto,filePath,
                    "PDF");

            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.APPLICATION_PDF);

            return new ResponseEntity<>(media, headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
