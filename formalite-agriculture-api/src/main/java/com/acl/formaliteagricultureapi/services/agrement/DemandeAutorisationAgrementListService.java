package com.acl.formaliteagricultureapi.services.agrement;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 04/10/2024
 * @project formalite-agriculture-api
 */
@Service
public interface DemandeAutorisationAgrementListService {

    ResponseEntity<?> listDemandeAutorisationAgrement(Etat etat, String compteDemandeur);

    ResponseEntity<?> listAutorisationAgrements(Etat etat);

}
