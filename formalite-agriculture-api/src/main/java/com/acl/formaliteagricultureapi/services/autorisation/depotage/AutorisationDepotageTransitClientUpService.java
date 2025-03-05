package com.acl.formaliteagricultureapi.services.autorisation.depotage;

import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientListDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 29/08/2024
 * @project formalite-agriculture-api
 */
public interface AutorisationDepotageTransitClientUpService {

    ResponseEntity<?> updateDemande(AutorisationDepotageTransitClientListDto request) throws Exception;

}
