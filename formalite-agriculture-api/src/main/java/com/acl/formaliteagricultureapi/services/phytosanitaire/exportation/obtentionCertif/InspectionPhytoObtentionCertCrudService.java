package com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.obtentionCertif;

import com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection.InspectionPhytoObtentionCertifDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientDto;
import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
public interface InspectionPhytoObtentionCertCrudService {

    ResponseEntity<?> create(InspectionPhytoObtentionCertifDto request);

    ResponseEntity<?> validerDemande(PhytosanitaireNavireClientListDto request) throws Exception;

    ResponseEntity<?> cancel(UpdateFormaliteDto request);
}
