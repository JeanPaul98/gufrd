package com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux.AutorisationEnlevementClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 28/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationEnlevementClientUpService {

    ResponseEntity<?> updateDemande(AutorisationImportationUpdateDto request) throws Exception;

}
