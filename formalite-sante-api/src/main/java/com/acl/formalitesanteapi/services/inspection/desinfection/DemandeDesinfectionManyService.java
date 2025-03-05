package com.acl.formalitesanteapi.services.inspection.desinfection;

import com.acl.formalitesanteapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/09/2024
 * @project formalite-sante-api
 */
@Service
public interface DemandeDesinfectionManyService {

    ResponseEntity<?> listInspection(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();
}
