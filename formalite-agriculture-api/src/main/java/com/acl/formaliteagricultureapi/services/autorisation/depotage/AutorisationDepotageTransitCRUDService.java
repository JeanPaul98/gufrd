package com.acl.formaliteagricultureapi.services.autorisation.depotage;

import com.acl.formaliteagricultureapi.dto.imports.depotage.AutorisationDepotageTransitClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationDepotageTransitCRUDService {

    ResponseEntity<?> create(AutorisationDepotageTransitClientDto autorisationDepotageTransitClientDto);

}
