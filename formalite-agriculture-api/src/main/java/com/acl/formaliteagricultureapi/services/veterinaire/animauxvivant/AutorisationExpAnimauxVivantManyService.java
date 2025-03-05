package com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationExpAnimauxVivantManyService {

    ResponseEntity<?> listAutorisation(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

    ResponseEntity<?> listAutorisationByCompte(Etat etat, String ref, String compte);

}
