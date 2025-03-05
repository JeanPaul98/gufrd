package com.acl.formaliteagricultureapi.services.processVerval.cargaison;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PvPhytoCargaisonManyService {

    ResponseEntity<?> listProcessVervale(String ref);


}
