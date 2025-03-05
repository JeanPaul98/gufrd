package com.acl.formaliteagricultureapi.services.agrement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AgrementListService {
    ResponseEntity<?> listAgrement();

    ResponseEntity<?> getAgrementBySociete(String nif);

}
