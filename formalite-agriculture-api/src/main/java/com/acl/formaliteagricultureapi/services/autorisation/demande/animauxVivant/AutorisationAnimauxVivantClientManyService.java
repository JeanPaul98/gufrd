package com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationAnimauxVivantClientManyService {

    ResponseEntity<?>listAutorisationAnimaux(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

    ResponseEntity<?>listAutorisationAnimauxByCompte(Etat etat, String ref, String compte);

}
