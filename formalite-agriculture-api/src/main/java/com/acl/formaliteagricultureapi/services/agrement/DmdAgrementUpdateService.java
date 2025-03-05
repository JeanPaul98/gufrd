package com.acl.formaliteagricultureapi.services.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.DmdAgrementUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Service
public interface DmdAgrementUpdateService {

     ResponseEntity<?> accepter(DmdAgrementUpdateDto request);

     ResponseEntity<?> rejecter(DmdAgrementUpdateDto request);

     ResponseEntity<?> traiter(DmdAgrementUpdateDto request);

}
