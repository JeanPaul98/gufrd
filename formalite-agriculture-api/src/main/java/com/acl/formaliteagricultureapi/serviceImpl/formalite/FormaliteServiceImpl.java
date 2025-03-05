package com.acl.formaliteagricultureapi.serviceImpl.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.dto.formalite.FormaliteUpdateFeedDto;
import com.acl.formaliteagricultureapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.FeedbackSrvPaiement;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.repository.FeedBackRepository;
import com.acl.formaliteagricultureapi.repository.FeedbackSrvPaiementRepository;
import com.acl.formaliteagricultureapi.serviceImpl.autorisation.enlevement.AutorisationEnlevementClientManyServiceImpl;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.services.feedback.FeedbackService;
import com.acl.formaliteagricultureapi.services.feedbackPaiement.FeedbackPaiementSendService;
import com.acl.formaliteagricultureapi.services.formalite.FormaliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public class FormaliteServiceImpl implements FormaliteService {

    private final Logger logger= LoggerFactory.getLogger(FormaliteServiceImpl.class);

    private final FormaliteRepository formaliteRepository;

    private final FeedBackRepository feedBackRepository;

    private  final FeedbackService feedbackService;

    private final FeedbackSrvPaiementRepository feedbackSrvPaiementRepository;

    private final FeedbackPaiementSendService feedbackPaiementSendService;

    @Autowired
    private Environment env;

    public FormaliteServiceImpl(FormaliteRepository formaliteRepository, FeedBackRepository feedBackRepository, FeedbackService feedbackService, FeedbackSrvPaiementRepository feedbackSrvPaiementRepository, FeedbackPaiementSendService feedbackPaiementSendService) {
        this.formaliteRepository = formaliteRepository;
        this.feedBackRepository = feedBackRepository;
        this.feedbackService = feedbackService;
        this.feedbackSrvPaiementRepository = feedbackSrvPaiementRepository;
        this.feedbackPaiementSendService = feedbackPaiementSendService;
    }

    @Override
    public ResponseEntity<?> soumettreDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());

        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.SOUMIS);
            optionalFormalite.get().setDateSoumise(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.succes"),savedFormalite.getNumeroDossier()),
                    HttpStatus.OK );

        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> accepterDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto) {

        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.ACCEPTER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }

    }



    @Override
    public ResponseEntity<?> annulerDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.ANNULER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }    }

    @Override
    public ResponseEntity<?> rejeterDemandeAutorisation(RejetFormaliteDto request) {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());
        if (optionalFormalite.isPresent()) {
            optionalFormalite.get().setEtat(Etat.REJETER);
            optionalFormalite.get().setDateRejet(new Date());
            optionalFormalite.get().setMotifAnnulation(request.getMotifRejet());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), savedFormalite.getId()),HttpStatus.OK );

        }else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.echec")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> accepterDemandeFeedBack(UpdateFormaliteDto updateFormaliteDto) throws IOException {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(updateFormaliteDto.getIdFormalite());

        if (optionalFormalite.isPresent()) {

            Optional<FeedBackPublic> feedBackPublic = feedBackRepository.findByIdFormalite(optionalFormalite.get().getId());

            optionalFormalite.get().setEtat(Etat.ACCEPTER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            if (feedBackPublic.isPresent()) {
                FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();
                feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.get().getFeedbackTaskId());
                feedBackFormaliteDto.setProcess(feedBackPublic.get().getProcess());
                feedBackFormaliteDto.setRecord(feedBackPublic.get().getRecord());
                feedBackFormaliteDto.setOrder(feedBackPublic.get().getOrder());
                feedBackFormaliteDto.setStep(feedBackPublic.get().getStep());
                feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.accepter"));
                feedBackFormaliteDto.setMessage(env.getProperty("message.atd.message.accepter"));

                FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
                dtoFormalite.setEtat(Etat.ACCEPTE.getLabel());

                logger.info("Statut de paiement {}", updateFormaliteDto.getStatutPaiement().name());

                dtoFormalite.setStatutPaiement(updateFormaliteDto.getStatutPaiement().name());
                dtoFormalite.setMontantRedevance(optionalFormalite.get().getMontantRedevance());
                feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);
                return     feedbackService.sendFeedBack(feedBackFormaliteDto, Etat.ACCEPTE.getLabel());

            }else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.not.found.entity")),HttpStatus.OK );
            }

        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> rejeterDemandeFeedback(RejetFormaliteDto request) throws IOException {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());

        if (optionalFormalite.isPresent()) {
            Optional<FeedBackPublic> feedBackPublic = feedBackRepository.findByIdFormalite(optionalFormalite.get().getId());

            optionalFormalite.get().setEtat(Etat.REJETER);
            optionalFormalite.get().setDateRejet(new Date());
            optionalFormalite.get().setMotifAnnulation(request.getMotifRejet());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            if (feedBackPublic.isPresent()) {
                FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();
                feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.get().getFeedbackTaskId());
                feedBackFormaliteDto.setProcess(feedBackPublic.get().getProcess());
                feedBackFormaliteDto.setRecord(feedBackPublic.get().getRecord());
                feedBackFormaliteDto.setOrder(feedBackPublic.get().getOrder());
                feedBackFormaliteDto.setStep(feedBackPublic.get().getStep());
                feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.rejet"));
                feedBackFormaliteDto.setMessage(env.getProperty("message.atd.message.rejet"));
                feedBackFormaliteDto.setMotifRejet(request.getMotifRejet());

                FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
                dtoFormalite.setEtat(Etat.REJETER.getLabel());
                dtoFormalite.setStatutPaiement(StatutPaiement.A_PAYER.getLabel());
                dtoFormalite.setMontantRedevance(optionalFormalite.get().getMontantRedevance());
                feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);

                return     feedbackService.sendFeedBack(feedBackFormaliteDto, Etat.REJETER.getLabel());

            }else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.not.found.entity")),HttpStatus.OK );
            }

        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> confirmPaiement(UpdateFormaliteDto request) throws IOException {

        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());

        if (optionalFormalite.isPresent()) {

            List<FeedbackSrvPaiement> feedBackPublic = feedbackSrvPaiementRepository.findByFormaliteList(optionalFormalite.get().getId());

            optionalFormalite.get().setEtat(Etat.TRAITER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            if (!feedBackPublic.isEmpty()) {
                FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();

                feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.get(0).getFeedbackTaskId());
                feedBackFormaliteDto.setProcess(feedBackPublic.get(0).getProcess());
                feedBackFormaliteDto.setRecord(feedBackPublic.get(0).getRecord());
                feedBackFormaliteDto.setOrder(feedBackPublic.get(0).getOrder());
                feedBackFormaliteDto.setStep(feedBackPublic.get(0).getStep());
                feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setMessage(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setTransactionId(feedBackPublic.get(0).getTransactionId());

                FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
                dtoFormalite.setEtat(Etat.TRAITER.getLabel());
                dtoFormalite.setStatutPaiement(request.getStatutPaiement().name());
                dtoFormalite.setMontantRedevance(optionalFormalite.get().getMontantRedevance());
                feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);
                String url = "http://102.164.230.196:9090/api/v1/report/autorisation/pdf?idFormalite="+optionalFormalite.get().getId();
                feedBackFormaliteDto.setPiece(url);
                return     feedbackPaiementSendService.sendFeedBack(feedBackFormaliteDto,
                        Etat.PAYER.getLabel());

            }else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.not.found.entity")),HttpStatus.OK );
            }

        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> confirmPaiementPhyto(UpdateFormaliteDto request) throws IOException {

        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());

        if (optionalFormalite.isPresent()) {

            Optional<FeedbackSrvPaiement> feedBackPublic = feedbackSrvPaiementRepository.findByFormalite(optionalFormalite.get().getId());

            optionalFormalite.get().setEtat(Etat.TRAITER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            if (feedBackPublic.isPresent()) {
                FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();

                feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.get().getFeedbackTaskId());
                feedBackFormaliteDto.setProcess(feedBackPublic.get().getProcess());
                feedBackFormaliteDto.setRecord(feedBackPublic.get().getRecord());
                feedBackFormaliteDto.setOrder(feedBackPublic.get().getOrder());
                feedBackFormaliteDto.setStep(feedBackPublic.get().getStep());
                feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setMessage(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setTransactionId(feedBackPublic.get().getTransactionId());

                FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
                dtoFormalite.setEtat(Etat.TRAITER.getLabel());
                dtoFormalite.setStatutPaiement(request.getStatutPaiement().name());
                dtoFormalite.setMontantRedevance(optionalFormalite.get().getMontantRedevance());
                feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);
                String url = "https://agri-sandbox.guford.com/api/v1/report/processVerbal/pv/pdf?idFormalite="+optionalFormalite.get().getId();
                feedBackFormaliteDto.setPiece(url);
                return     feedbackPaiementSendService.sendFeedBack(feedBackFormaliteDto,
                        Etat.PAYER.getLabel());

            }else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.not.found.entity")),HttpStatus.OK );
            }

        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> confirmCertificat(UpdateFormaliteDto request) throws IOException {
        Optional<Formalite> optionalFormalite = formaliteRepository.findById(request.getIdFormalite());

        if (optionalFormalite.isPresent()) {

            Optional<FeedbackSrvPaiement> feedBackPublic = feedbackSrvPaiementRepository.findByFormalite(optionalFormalite.get().getId());

            optionalFormalite.get().setEtat(Etat.TRAITER);
            optionalFormalite.get().setDateAccepte(new Date());
            optionalFormalite.get().setUpdateAt(new Date());
            Formalite savedFormalite = formaliteRepository.save(optionalFormalite.get());

            if (feedBackPublic.isPresent()) {
                FeedBackFormaliteDto feedBackFormaliteDto = new FeedBackFormaliteDto();

                feedBackFormaliteDto.setFeedbackTaskId(feedBackPublic.get().getFeedbackTaskId());
                feedBackFormaliteDto.setProcess(feedBackPublic.get().getProcess());
                feedBackFormaliteDto.setRecord(feedBackPublic.get().getRecord());
                feedBackFormaliteDto.setOrder(feedBackPublic.get().getOrder());
                feedBackFormaliteDto.setStep(feedBackPublic.get().getStep());
                feedBackFormaliteDto.setTitle(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setMessage(env.getProperty("message.atd.title.paiement"));
                feedBackFormaliteDto.setTransactionId(feedBackPublic.get().getTransactionId());

                FormaliteUpdateFeedDto dtoFormalite = new FormaliteUpdateFeedDto();
                dtoFormalite.setEtat(Etat.TRAITER.getLabel());
                dtoFormalite.setStatutPaiement(request.getStatutPaiement().name());
                dtoFormalite.setMontantRedevance(optionalFormalite.get().getMontantRedevance());
                feedBackFormaliteDto.setFormaliteUpdateFeedDto(dtoFormalite);
                String url = "http://102.164.230.196:4206/api/v1/report/certificat/cire/pdf?idFormalite="+optionalFormalite.get().getId();
                feedBackFormaliteDto.setPiece(url);
                return     feedbackPaiementSendService.sendFeedBack(feedBackFormaliteDto,
                        Etat.PAYER.getLabel());

            }else  {
                return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                        env.getProperty("message.not.found.entity")),HttpStatus.OK );
            }

        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.not.found.entity")),HttpStatus.OK );
        }
    }
}
