package com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 29/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationAnimauxVivantUpService {

    ResponseEntity<?> updateDemande(AutorisationImportationUpdateDto request) throws Exception;

}
