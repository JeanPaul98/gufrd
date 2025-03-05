package com.acl.formaliteagricultureapi.services.feedbackPaiement;

import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public interface FeedbackPaiementSendService {

    ResponseEntity<?> sendFeedBack(FeedBackFormaliteDto request, String etat) throws IOException;

}
