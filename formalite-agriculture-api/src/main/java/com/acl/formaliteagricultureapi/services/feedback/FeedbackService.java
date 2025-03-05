package com.acl.formaliteagricultureapi.services.feedback;

import com.acl.formaliteagricultureapi.dto.feedback.FeedBackDto;
import com.acl.formaliteagricultureapi.dto.feedback.FeedBackUpdateDto;
import com.acl.formaliteagricultureapi.dto.formalite.FeedBackFormaliteDto;
import com.acl.formaliteagricultureapi.models.FeedBackPublic;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Service
public interface FeedbackService {

    ResponseEntity<?> create(FeedBackDto request);


    ResponseEntity<?> sendFeedBack(FeedBackFormaliteDto request, String etat) throws IOException;

    boolean feedBackTraitement(FeedBackFormaliteDto request, String etat) throws IOException;

    ResponseEntity<?> update(FeedBackUpdateDto request);
}
