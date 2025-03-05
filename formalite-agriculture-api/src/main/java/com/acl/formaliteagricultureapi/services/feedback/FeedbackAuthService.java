package com.acl.formaliteagricultureapi.services.feedback;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author kol on 10/7/24
 * @project formalite-agriculture-api
 */
@Service
public interface FeedbackAuthService {

   String generateTokens() throws IOException;
}
