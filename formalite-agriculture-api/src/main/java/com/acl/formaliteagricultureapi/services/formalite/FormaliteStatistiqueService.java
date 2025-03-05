package com.acl.formaliteagricultureapi.services.formalite;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
@Service
public interface FormaliteStatistiqueService {

    ResponseEntity<?> getAllFormaliteStatistiquePhyto();
}
