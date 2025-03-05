package com.acl.formaliteagricultureapi.services.agrement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface TypeAgrementListService {

    ResponseEntity<?> listTypeAgrement();
}
