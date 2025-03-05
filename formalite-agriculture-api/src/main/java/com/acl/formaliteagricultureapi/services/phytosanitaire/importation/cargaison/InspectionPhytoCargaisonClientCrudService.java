package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.cargaison;


import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientDto;
import com.acl.formaliteagricultureapi.dto.imports.cargaison.PhytosanitaireCargaisonClientListDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientListDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 23/08/2024
 * @project formalite-agriculture-api
 */
public interface InspectionPhytoCargaisonClientCrudService {
    ResponseEntity<?> create(PhytosanitaireCargaisonClientDto request);

    ResponseEntity<?> validerDemande(PhytosanitaireCargaisonClientListDto request) throws Exception;

    ResponseEntity<?> cancel(PhytosanitaireCargaisonClientListDto request);
}
