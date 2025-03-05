package com.acl.formaliteenvironnementapi.services.enlevement;

import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 12/09/2024
 * @project formalite-environnement-api
 */
@Service
public interface EnlevementDechetsManyService {

    ResponseEntity<?> listAutorisation(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();
}
