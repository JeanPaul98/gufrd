package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.navire;


import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface InspPhytoNavireClientCrudService {

    ResponseEntity<?> create(PhytosanitaireNavireClientDto request);

    ResponseEntity<?> validerDemande(PhytosanitaireNavireClientListDto request) throws Exception;

    ResponseEntity<?> cancel(PhytosanitaireNavireClientListDto request);
}
