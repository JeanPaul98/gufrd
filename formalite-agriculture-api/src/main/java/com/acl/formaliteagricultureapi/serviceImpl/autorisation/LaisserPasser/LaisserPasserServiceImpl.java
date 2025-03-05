package com.acl.formaliteagricultureapi.serviceImpl.autorisation.LaisserPasser;

import com.acl.formaliteagricultureapi.dto.autorisation.LaisserPasserDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.Inspecteur;
import com.acl.formaliteagricultureapi.models.LaisserPasser;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.repository.InspecteurRepository;
import com.acl.formaliteagricultureapi.repository.LaisserPasserRepository;
import com.acl.formaliteagricultureapi.services.autorisation.laisserPasser.LaisserPasserService;
import com.acl.formaliteagricultureapi.services.report.ReportAutorisation;
import fr.opensagres.xdocreport.core.io.IOUtils;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class LaisserPasserServiceImpl implements LaisserPasserService {

    Logger logger= LoggerFactory.getLogger(LaisserPasserServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final InspecteurRepository inspecteurRepository;

    private final LaisserPasserRepository laisserPasserRepository;

    private final ReportAutorisation reportAutorisation;

    @Autowired
    private Environment env;

    public LaisserPasserServiceImpl(FormaliteRepository formaliteRepository,
                                    InspecteurRepository inspecteurRepository,
                                    LaisserPasserRepository laisserPasserRepository,
                                    ReportAutorisation reportAutorisation) {
        this.formaliteRepository = formaliteRepository;
        this.inspecteurRepository=inspecteurRepository;
        this.laisserPasserRepository=laisserPasserRepository;
        this.reportAutorisation=reportAutorisation;
    }

    @Override
    public ResponseEntity<?> createLaisserPasser(LaisserPasserDto laisserPasserDto) {
        Optional<Formalite>optionalFormalite= formaliteRepository.findById(laisserPasserDto.
                getIdFormalite());

        Optional<Inspecteur>optionalInspecteur= inspecteurRepository.findById(laisserPasserDto.
                getIdInspecteur());

        if(optionalFormalite.isPresent() && optionalInspecteur.isPresent()){
            LaisserPasser laisserPasser= new LaisserPasser(laisserPasserDto.getAbattoir(),
                    laisserPasserDto.getProvenance(),laisserPasserDto.getReferenceCertificat(),
                    laisserPasserDto.getReferenceConteneur(), laisserPasserDto.
                    getNumeroAutorisationDepotage(),laisserPasserDto.getDateAutorisationDepotage(),
                    laisserPasserDto.getMoyenTransport(),laisserPasserDto.getDateInspection(),
                    laisserPasserDto.getNumeroLaisserPasser(),new Date(),optionalFormalite.get(),
                    optionalInspecteur.get());

            LaisserPasser resultLaisserPasser= laisserPasserRepository.save(laisserPasser);
            optionalFormalite.get().setEtat(Etat.TRAITER);
            formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"),laisserPasserDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.notfound")), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<byte[]> generateLaisserPasser(UpdateFormaliteDto updateFormaliteDto) throws JRException, IOException {

        String filePath = "jasper/autorisationLaisserPasserReporting.jrxml";
        HttpHeaders headers = new HttpHeaders();

        if(updateFormaliteDto.getIdFormalite()!=null){
            LaisserPasser laisserPasser= laisserPasserRepository.findByIdFormalite(updateFormaliteDto.
                    getIdFormalite());
            if(laisserPasser!=null){
                LaisserPasserDto laisserPasserDto= new LaisserPasserDto(
                        laisserPasser.getFormalite().getId(),laisserPasser.getFormalite().getNomImportateur(),
                        laisserPasser.getInspecteur().getId(),laisserPasser.getDateInspection(),
                        laisserPasser.getProvenance(),laisserPasser.getAbattoir(),
                        laisserPasser.getReferenceCertificat(),laisserPasser.getReferenceConteneur(),
                        laisserPasser.getNumeroAutorisationDepotage(),laisserPasser.getDateAutorisationDepotage(),
                        laisserPasser.getMoyenTransport(),laisserPasser.getNumeroLaisserPasser());

                InputStream in= reportAutorisation.reportAutorisationLaisserPasser(laisserPasserDto,
                        filePath,"PDF");

                byte[] media = IOUtils.toByteArray(in);
                headers.setCacheControl(CacheControl.noCache().getHeaderValue());
                headers.setContentType(MediaType.APPLICATION_PDF);
                ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media,
                        headers, HttpStatus.OK);

                return responseEntity;

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
}
