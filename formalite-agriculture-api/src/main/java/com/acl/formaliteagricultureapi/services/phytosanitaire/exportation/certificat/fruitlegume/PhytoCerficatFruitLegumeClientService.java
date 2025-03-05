package com.acl.formaliteagricultureapi.services.phytosanitaire.exportation.certificat.fruitlegume;

import com.acl.formaliteagricultureapi.dto.exports.etablissementcertificat.PhytoCertifFruitLegumeClientDto;

import com.acl.formaliteagricultureapi.dto.imports.navire.PhytosanitaireNavireClientListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 27/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PhytoCerficatFruitLegumeClientService {

    ResponseEntity<?> create(PhytoCertifFruitLegumeClientDto request);

    ResponseEntity<?> validerDemande(PhytosanitaireNavireClientListDto request) throws Exception;

    ResponseEntity<?> cancel(PhytosanitaireNavireClientListDto request);
}
