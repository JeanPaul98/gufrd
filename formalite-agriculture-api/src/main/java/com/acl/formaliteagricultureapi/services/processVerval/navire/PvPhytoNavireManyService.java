package com.acl.formaliteagricultureapi.services.processVerval.navire;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PvPhytoNavireManyService {
    ResponseEntity<?> listProcessVervale(String ref);


}
