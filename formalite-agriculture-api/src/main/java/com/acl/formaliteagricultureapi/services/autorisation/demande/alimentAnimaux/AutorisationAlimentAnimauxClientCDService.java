package com.acl.formaliteagricultureapi.services.autorisation.demande.alimentAnimaux;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux.AutorisationAlimAnimauxClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationAlimentAnimauxClientCDService {

    ResponseEntity<?>create(AutorisationImportationDto request);

}
