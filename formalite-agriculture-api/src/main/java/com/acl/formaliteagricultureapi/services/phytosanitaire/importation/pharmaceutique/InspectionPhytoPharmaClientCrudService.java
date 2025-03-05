package com.acl.formaliteagricultureapi.services.phytosanitaire.importation.pharmaceutique;


import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientDto;
import com.acl.formaliteagricultureapi.dto.imports.phytopharmaceutique.PhytosanitaireProduitPhytopharmaClientListDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 23/08/2024
 * @project formalite-agriculture-api
 */
public interface InspectionPhytoPharmaClientCrudService {
    ResponseEntity<?> create(PhytosanitaireProduitPhytopharmaClientDto request);

    ResponseEntity<?> validerDemande(PhytosanitaireProduitPhytopharmaClientListDto request) throws Exception;

    ResponseEntity<?> cancel(PhytosanitaireProduitPhytopharmaClientListDto request);
}
