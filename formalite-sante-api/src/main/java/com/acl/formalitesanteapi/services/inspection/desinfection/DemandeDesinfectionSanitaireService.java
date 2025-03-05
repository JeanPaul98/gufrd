package com.acl.formalitesanteapi.services.inspection.desinfection;

import com.acl.formalitesanteapi.dto.inspection.depotage.InspectionSanitaireDepotageDto;
import com.acl.formalitesanteapi.dto.inspection.desinfection.DemandeDesinfectionDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
public interface DemandeDesinfectionSanitaireService {

    ResponseEntity<?> create(DemandeDesinfectionDto request);
}
