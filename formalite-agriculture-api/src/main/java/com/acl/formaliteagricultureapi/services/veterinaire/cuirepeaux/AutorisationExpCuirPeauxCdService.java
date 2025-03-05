package com.acl.formaliteagricultureapi.services.veterinaire.cuirepeaux;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirsPeauxListDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */

public interface AutorisationExpCuirPeauxCdService {

    ResponseEntity<?> create(AutorisationExpCuirePeauxDto request);

    ResponseEntity<?> validerDemande(AutorisationExpCuirsPeauxListDto request) throws Exception;

}
