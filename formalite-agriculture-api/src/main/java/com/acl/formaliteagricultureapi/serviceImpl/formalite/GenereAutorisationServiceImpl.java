package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.converter.ProduitConverter;
import com.acl.formaliteagricultureapi.dto.atd.QrCodeDto;
import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.dto.formalite.FormaliteUpdateFeedDto;
import com.acl.formaliteagricultureapi.dto.formalite.GenereAutorisationDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.ReportDetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.models.Autorisation;
import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AutorisationRepository;
import com.acl.formaliteagricultureapi.repository.DetAutorisationProduitRepository;
import com.acl.formaliteagricultureapi.repository.FeedBackRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.serviceImpl.utils.UtilServiceImpl;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackService;
import com.acl.formaliteagricultureapi.services.formalite.GenereAutorisationService;
import com.acl.formaliteagricultureapi.services.qrcode.QrcodeService;
import com.acl.formaliteagricultureapi.services.report.ReportAutorisation;
import com.acl.formaliteagricultureapi.services.utils.UtilServices;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import fr.opensagres.xdocreport.core.io.IOUtils;

/**
 * @author Zansouyé on 09/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class GenereAutorisationServiceImpl implements GenereAutorisationService {

    Logger logger= LoggerFactory.getLogger(GenereAutorisationServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final AutorisationRepository autorisationRepository;

    private final DetAutorisationProduitRepository detAutorisationProduitRepository;

    private final ReportAutorisation reportAutorisation;

    private final FeedBackRepository feedBackRepository;

    private final UtilServices utilServices;

    private final FeedbackService feedbackService;

    private final QrcodeService qrcodeService;

    @Autowired
    private Environment env;

    @Autowired
    private ProduitConverter produitConverter;
    public GenereAutorisationServiceImpl(FormaliteRepository formaliteRepository, AutorisationRepository autorisationRepository,
                                         DetAutorisationProduitRepository detAutorisationProduitRepository,
                                         ReportAutorisation reportAutorisation, FeedBackRepository feedBackRepository, UtilServices utilServices, FeedbackService feedbackService, QrcodeService qrcodeService) {

        this.formaliteRepository = formaliteRepository;
        this.autorisationRepository = autorisationRepository;
        this.detAutorisationProduitRepository=detAutorisationProduitRepository;
        this.reportAutorisation=reportAutorisation;
        this.feedBackRepository = feedBackRepository;
        this.utilServices = utilServices;
        this.feedbackService = feedbackService;
        this.qrcodeService = qrcodeService;
    }

    @Override
    public ResponseEntity<byte[]> genereAutorisation(UpdateFormaliteDto updateFormaliteDto)
            throws JRException, IOException {

        String filePath = "jasper/autorisationReporting.jrxml";
        HttpHeaders headers = new HttpHeaders();

        Optional<Formalite>optionalFormalite= formaliteRepository.findById(updateFormaliteDto.getIdFormalite());

        if(optionalFormalite.isPresent()){

            GenereAutorisationDto genereAutorisationDto= new GenereAutorisationDto();
            genereAutorisationDto.setNomImportateur(optionalFormalite.get().getNomImportateur());

            List<DetAutorisationProduit> detAutorisationProduitList=detAutorisationProduitRepository.
                    findByAutorisationId(optionalFormalite.get().getAutorisation().getId());

            List<ReportDetAutorisationProduitDto> detAutorisationProduitDtos= new ArrayList<>();

            detAutorisationProduitList.forEach(data->{
                ReportDetAutorisationProduitDto oneDetAutorisationProduitDto= new
                        ReportDetAutorisationProduitDto();

                oneDetAutorisationProduitDto.setConteneur(data.getConteneur());
                oneDetAutorisationProduitDto.setOrigine(data.getOrigine());
                oneDetAutorisationProduitDto.setQuantite(data.getQuantite());
                oneDetAutorisationProduitDto.setUnite(data.getUnite());
                oneDetAutorisationProduitDto.setNombreCarton(data.getNombreCarton());
                oneDetAutorisationProduitDto.setDateProduction(data.getDateProduction());
                oneDetAutorisationProduitDto.setPoids(data.getPoidNet());

                ProduitDto produitDto= new ProduitDto();
                produitDto=produitConverter.convertDtoToEntity(data.getProduit());


                oneDetAutorisationProduitDto.setLibelleProduit(produitDto.getLibelle());
                detAutorisationProduitDtos.add(oneDetAutorisationProduitDto);
            });

            genereAutorisationDto.setDetAutorisationProduitDtos(detAutorisationProduitDtos);
            genereAutorisationDto.setQrCode(genereQrCode(optionalFormalite.get().getNomImportateur(),
                    optionalFormalite.get().getNumGenere()));


            InputStream in= reportAutorisation.reportAutorisationConfig(genereAutorisationDto,filePath,
                    "PDF",optionalFormalite.get());

            byte[] media = IOUtils.toByteArray(in);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.add("transfer-encoding", "chunked");
            headers.setContentType(MediaType.APPLICATION_PDF);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);

            updateFormalite(optionalFormalite.get());

      //      Optional<FeedBackPublic> feedBackPublic = feedBackRepository.findByIdFormalite(optionalFormalite.get().getId());

         //   String url = "http://102.164.230.196:9090/api/v1/report/autorisation/pdf?idFormalite="+optionalFormalite.get().getId();



            return responseEntity;
            /*return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), genereAutorisationDto), HttpStatus.OK);*/
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Formalite updateFormalite(Formalite formalite) {
        formalite.setEtat(Etat.TRAITER);
        formalite.setDateTraitement(new Date());
        formalite.setUpdateAt(new Date());
        Optional<Autorisation> optionalautorisation = autorisationRepository.findById(formalite.getAutorisation().getId());
            optionalautorisation.get().setNumroAutorisation(utilServices.generateNumDemande(
                    env.getProperty("message.code.genere.numero.autorisation")));

      return formaliteRepository.save(formalite);
    }

    private  boolean sendFeadBackTraitement(FeedBackPublic feedBackPublic, String url) throws IOException {

        FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();
        feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.getFeedbackTaskId());
        feedBackFormaliteDto.setProcess(feedBackPublic.getProcess());
        feedBackFormaliteDto.setRecord(feedBackPublic.getRecord());
        feedBackFormaliteDto.setOrder(feedBackPublic.getOrder());
        feedBackFormaliteDto.setStep(feedBackPublic.getStep());
        feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.traiter"));
        feedBackFormaliteDto.setMessage(env.getProperty("message.atd.title.traiter"));

        FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
        dtoFormalite.setEtat(Etat.TRAITER.getLabel());
        dtoFormalite.setStatutPaiement(StatutPaiement.PAYE.getLabel());
        feedBackFormaliteDto.setPiece(url);
        feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);

        return   feedbackService.feedBackTraitement(feedBackFormaliteDto, Etat.TRAITE.getLabel());

    }

    private String genereQrCode(String nomImportateur, String numeroDemande) throws IOException {
        QrCodeDto qrCodeDto = new QrCodeDto();
        qrCodeDto.setTypeDocument("AUTORISATION DE DEPOTAGE");
        qrCodeDto.setRefDocument(numeroDemande);
        qrCodeDto.setNif("NIF234899");
        qrCodeDto.setExp("2024-10-31");
        qrCodeDto.setSlug("pal_autorisation_depotage");
        qrCodeDto.setCat("CAT");
        qrCodeDto.setNomSociete(nomImportateur);
        qrCodeDto.setAutoriteCompetente("DIRECTION DE L'ELEVAGE");
        qrCodeDto.setModeUtilisation("UNIQUE");

        return qrcodeService.generateQrCode(qrCodeDto);
    }

}
