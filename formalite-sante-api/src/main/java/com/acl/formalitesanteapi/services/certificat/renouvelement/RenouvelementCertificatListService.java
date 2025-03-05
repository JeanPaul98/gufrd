package com.acl.formalitesanteapi.services.certificat.renouvelement;

import com.acl.formalitesanteapi.models.enumeration.Etat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Service
public interface RenouvelementCertificatListService {

    ResponseEntity<?> listCertificat(Etat etat, String ref);

}
