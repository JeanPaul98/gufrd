package com.acl.formaliteagricultureapi.serviceImpl.feedback.paiement;

import com.acl.formaliteagricultureapi.converter.FeedbackPaiementConverter;
import com.acl.formaliteagricultureapi.dto.feedback.paiement.FeedbackPaiementDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.FeedbackSrvPaiement;
import com.acl.formaliteagricultureapi.models.Formalite;
import com.acl.formaliteagricultureapi.models.enumeration.StatutPaiement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.FeedbackSrvPaiementRepository;
import com.acl.formaliteagricultureapi.repository.FormaliteRepository;
import com.acl.formaliteagricultureapi.services.feedbackPaiement.FeedbackPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public class FeedbackPaiementServiceImpl implements FeedbackPaiementService {

    private final  FeedbackSrvPaiementRepository feedbackSrvPaiementRepository;

    private final FeedbackPaiementConverter feedbackPaiementConverter;

    private final FormaliteRepository formaliteRepository;

    @Autowired
    private Environment env;

    public FeedbackPaiementServiceImpl(FeedbackSrvPaiementRepository feedbackSrvPaiementRepository, FeedbackPaiementConverter feedbackPaiementConverter, FormaliteRepository formaliteRepository) {
        this.feedbackSrvPaiementRepository = feedbackSrvPaiementRepository;
        this.feedbackPaiementConverter = feedbackPaiementConverter;
        this.formaliteRepository = formaliteRepository;
    }

    @Override
    public ResponseEntity<?> create(FeedbackPaiementDto request) {

        Optional<Formalite> formalite = formaliteRepository.
                findById(request.getIdFormalite());

        if (formalite.isPresent()) {
            FeedbackSrvPaiement feedBackPublic = feedbackPaiementConverter.convertDToEntity(request);
            feedBackPublic.setFormalite(formalite.get());
            feedBackPublic.setTransactionId("TR"+new Date().getTime());
            feedBackPublic.setCreatedAt(new Date());
            feedbackSrvPaiementRepository.save(feedBackPublic);
            formalite.get().setStatutPaiement(StatutPaiement.PAYER);
            formalite.get().setUpdateAt(new Date());
            formaliteRepository.save(formalite.get());

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.succes"),
                    request),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),env.getProperty("message.not.found.entity"),
                    request.getIdFormalite()),HttpStatus.OK);
        }

    }
}
