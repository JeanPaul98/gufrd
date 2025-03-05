package com.acl.formaliteagricultureapi.services.feedbackPaiement;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.paiement.FeedbackPaiementDto;
import com.acl.formaliteagricultureapi.models.FeedbackSrvPaiement;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
public interface FeedbackPaiementService {

    ResponseEntity<?> create(FeedbackPaiementDto request);
}
