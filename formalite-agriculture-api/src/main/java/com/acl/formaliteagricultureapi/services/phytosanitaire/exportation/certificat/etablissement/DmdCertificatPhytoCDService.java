package com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.certificat.etablissement;

import com.acl.formaliteagricultureapi.dto.exports.vegetaux.certificat.DmdCerticatPhytoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface DmdCertificatPhytoCDService {

    ResponseEntity<?> create(DmdCerticatPhytoDto request);

}
