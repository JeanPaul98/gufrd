package com.acl.formaliteagricultureapi.services.veterinaire.cire;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationExpCireManyService {

    ResponseEntity<?> listeAutorisation(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

}
