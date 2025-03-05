package com.acl.formaliteagricultureapi.services.autorisation.demande.medicament;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/**
 * @author Zansouyé on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationEnvMedicamentClientManyService {

    ResponseEntity<?>listAutorisationEnlevement(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();

    ResponseEntity<?>listAutorisationEnvMedicamentByCompte(Etat etat, String ref, String compte);

}
