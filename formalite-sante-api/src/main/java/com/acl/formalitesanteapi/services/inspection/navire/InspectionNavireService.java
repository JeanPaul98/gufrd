package com.acl.formalitesanteapi.services.inspection.navire;

import com.acl.formalitesanteapi.dto.certificat.RenouvelementCertificatDto;
import com.acl.formalitesanteapi.dto.inspection.navire.InspectionNavireDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Service
public interface InspectionNavireService {

    ResponseEntity<?> create(InspectionNavireDto request);

}
