package com.acl.formaliteagricultureapi.services.autorisation.demande.consommation;

import com.acl.formaliteagricultureapi.dto.autorisation.demande.AutorisationImportationDto;
import com.acl.formaliteagricultureapi.dto.imports.demande.consommation.AutorisationConsommationProduitClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationConsommationLocalClientCRUDService {

    ResponseEntity<?>create(AutorisationImportationDto request);

}
