package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison;

import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonUpClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 20/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface InspectionPhytoCargaisonUpService {

    ResponseEntity<?> updateDemande(PhytosanitaireCargaisonUpClientDto request) throws Exception;

}
