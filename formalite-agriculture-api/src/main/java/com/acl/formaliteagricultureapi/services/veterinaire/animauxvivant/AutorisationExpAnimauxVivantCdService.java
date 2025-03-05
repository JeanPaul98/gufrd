package com.acl.formaliteagricultureapi.services.veterinaire.animauxvivant;

import com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant.AutorisationExpAnimauxVivantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AutorisationExpAnimauxVivantCdService {

    ResponseEntity<?> create(AutorisationExpAnimauxVivantDto request);

}
