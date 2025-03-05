package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface InspectionPhtyoNavireManyService {

    ResponseEntity<?>listAutorisationEnlevement(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

    ResponseEntity<?> listPhytosanitaireByCompte(Etat etat, String ref, String compte);
}
