package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire;

import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 20/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface InspectionPhytoNavireUpService {

    ResponseEntity<?> updateDemande(PhytosanitaireNavireClientListDto request) throws Exception;

}
