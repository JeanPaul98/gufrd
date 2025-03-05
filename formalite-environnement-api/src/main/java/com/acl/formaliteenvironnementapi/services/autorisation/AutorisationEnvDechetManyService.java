package com.acl.formaliteenvironnementapi.services.autorisation;

import com.acl.formaliteenvironnementapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Service
public interface AutorisationEnvDechetManyService {

    ResponseEntity<?> listAutorisation(Etat etat, String ref);

    ResponseEntity<?> listStatDemande();
}
