package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.GenerateCertificatDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.repository.DetCertificatProduitRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.serviceImpl.report.ReportCerficatServiceImpl;
import com.acl.formaliteagricultureapi.services.formalite.GenerateCertificatService;
import com.acl.formaliteagricultureapi.services.report.ReportCertificatService;
import fr.opensagres.xdocreport.core.io.IOUtils;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Service
public class GenerateCertificatServiceImpl implements GenerateCertificatService {

    private final FormaliteRepository formaliteRepository;

    Logger logger= LoggerFactory.getLogger(GenerateCertificatServiceImpl.class);


    private final ReportCertificatService reportCertificatService;

    private final DetCertificatProduitRepository detCertificatProduitRepository;

    public GenerateCertificatServiceImpl(FormaliteRepository formaliteRepository, ReportCertificatService reportCertificatService, DetCertificatProduitRepository detCertificatProduitRepository) {
        this.formaliteRepository = formaliteRepository;
        this.reportCertificatService = reportCertificatService;
        this.detCertificatProduitRepository = detCertificatProduitRepository;
    }

    @Override
    public ResponseEntity<byte[]> generateCertificat(Long idFormalite) throws JRException, IOException {

        String filePath = "jasper/certificat_cire.jrxml";
        HttpHeaders headers = new HttpHeaders();
        Optional<Formalite> optionalFormalite= formaliteRepository.findById(idFormalite);

        if(optionalFormalite.isPresent()){

            GenerateCertificatDto generateCertificatDto= new GenerateCertificatDto();

            List<DetCertificatProduit> detCertificatProduits= detCertificatProduitRepository
                    .findByCertificatId(optionalFormalite.get().getCertificat().getId());

            logger.info("Generate Certificat {} ", detCertificatProduits.size() );

            if(!detCertificatProduits.isEmpty()){

                DetCertificatProduit detCertificatProduit= detCertificatProduitRepository
                        .findByCertificatId(optionalFormalite.get().getCertificat().getId()).get(0);

                logger.info("certificat {}", detCertificatProduit);


                logger.info("Id cerficat {}", optionalFormalite.get().getCertificat().getId());

                generateCertificatDto.setDestination(optionalFormalite.get().getCertificat().getAdressedestinat());
                generateCertificatDto.setPaysOrigine(detCertificatProduit.getPaysOrigineProvenance());
                generateCertificatDto.setNatureProduit(detCertificatProduit.getProduit().getLibelle());
                generateCertificatDto.setMoyensTransport(detCertificatProduit.getMoyenTransport());
                generateCertificatDto.setDestination(detCertificatProduit.getPaysDestination());
                generateCertificatDto.setNomAdressExpediteur(optionalFormalite.get().getCertificat().getNomexpediteur());
                generateCertificatDto.setLieuChargement(optionalFormalite.get().getCertificat().getLieuDechargement());
                generateCertificatDto.setPoidsTotal(detCertificatProduit.getPoidsTotal());
                generateCertificatDto.setNombreUnites(detCertificatProduit.getNombre());
                generateCertificatDto.setTypeCertificat(optionalFormalite.get().getCertificat().getTypeCertificat().getLibelle());

                logger.info("Generation {}", generateCertificatDto);

                List<DetCertificatProduitDto> detCertificatProduitDtos= new ArrayList<>();
                detCertificatProduits.forEach(data -> {
                    DetCertificatProduitDto dto = new DetCertificatProduitDto();
                    dto.setNatureProduit(data.getProduit().getLibelle());
                    dto.setRace(data.getRace());
                    dto.setFournisseur(data.getFournisseur());
                    dto.setConditionnement(data.getConditionnement());

                    detCertificatProduitDtos.add(dto);
                });

                generateCertificatDto.setDetProduits(detCertificatProduitDtos);
            }

            InputStream in= reportCertificatService.reportProcessConfig(generateCertificatDto,filePath,
                    "PDF",optionalFormalite.get());

            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.APPLICATION_PDF);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

           // updateFormalite(optionalFormalite.get());

            return responseEntity;
            /*return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), genereAutorisationDto), HttpStatus.OK);*/
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
