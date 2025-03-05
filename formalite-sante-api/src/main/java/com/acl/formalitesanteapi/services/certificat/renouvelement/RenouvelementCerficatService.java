package com.acl.formalitesanteapi.services.certificat.renouvelement;

import com.acl.formalitesanteapi.dto.certificat.RenouvelementCertificatDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Service
public interface RenouvelementCerficatService {

    ResponseEntity<?> create(RenouvelementCertificatDto request);


}
