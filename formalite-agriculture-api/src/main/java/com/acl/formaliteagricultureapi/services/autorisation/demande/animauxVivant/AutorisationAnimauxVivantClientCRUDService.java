package com.acl.formaliteagricultureapi.services.autorisation.demande.animauxVivant;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant.AutorisationAnimauxVivantsClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationAnimauxVivantClientCRUDService {

    ResponseEntity<?> create(AutorisationImportationDto request);

    ResponseEntity<?> validerDemande(AutorisationAnimauxVivantClientListDto request) throws Exception;

    ResponseEntity<?> cancel(AutorisationAnimauxVivantClientListDto request);
}
