package com.acl.formaliteagricultureapi.services.veterinaire.cire;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire.AutorisationExpCireDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire.AutorisationExpCireListDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirePeauxDto;
import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux.AutorisationExpCuirsPeauxListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationExpCireCdService {

    ResponseEntity<?> create(AutorisationExpCireDto request);

    ResponseEntity<?> validerDemande(AutorisationExpCireListDto request) throws Exception;

}
