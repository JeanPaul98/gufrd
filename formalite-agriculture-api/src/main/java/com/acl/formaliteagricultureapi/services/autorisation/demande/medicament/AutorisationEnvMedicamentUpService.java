package com.acl.formaliteagricultureapi.services.autorisation.demande.medicament;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationEnvMedicamentUpService {

    ResponseEntity<?> updateDemande(AutorisationImportationUpdateDto request) throws Exception;

}
