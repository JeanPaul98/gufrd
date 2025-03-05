package com.acl.formalitesanteapi.services.inspection.navire;

import com.acl.formalitesanteapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public interface InspectionNavireListService {

    ResponseEntity<?> listInspection(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();
}
