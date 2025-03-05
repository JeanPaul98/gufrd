package com.acl.formaliteagricultureapi.services.autorisation.depotage;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationDepotageTransitManyClientService {

    ResponseEntity<?> listAutorisationDepotage(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

    ResponseEntity<?>listAutorisatioByCompte(Etat etat, String ref, String compte);

}
