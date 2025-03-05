package com.acl.formalitesanteapi.services.inspection.depotage;

import com.acl.formalitesanteapi.dto.inspection.depotage.InspectionSanitaireDepotageDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public interface InspectionSanitaireDepotageService {

    ResponseEntity<?> create(InspectionSanitaireDepotageDto request);

}
