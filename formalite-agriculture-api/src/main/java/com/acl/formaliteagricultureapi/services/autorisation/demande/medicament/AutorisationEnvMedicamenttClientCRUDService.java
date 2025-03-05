package com.acl.formaliteagricultureapi.services.autorisation.demande.medicament;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.medicament.AutorisationEnlevementMedicamentClientDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.medicament.AutorisationEnlevementMedicamentClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationEnvMedicamenttClientCRUDService {

    ResponseEntity<?>create(AutorisationImportationDto request);

}
