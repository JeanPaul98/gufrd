package com.acl.formaliteagricultureapi.services.autorisation.demande.consommation;

import com.acl.formaliteagricultureapi.dto.imports.demande.consommation.AutorisationConsommationProduitClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 18/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationConsommationUpService {

    ResponseEntity<?> updateDemande(AutorisationConsommationProduitClientListDto request) throws Exception;

}
