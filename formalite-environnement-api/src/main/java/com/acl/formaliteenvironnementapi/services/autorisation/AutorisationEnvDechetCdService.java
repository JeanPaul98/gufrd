package com.acl.formaliteenvironnementapi.services.autorisation;

import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvDechetDto;
import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvListDechetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Service
public interface AutorisationEnvDechetCdService {

    ResponseEntity<?> create(AutorisationEnvDechetDto request);

    ResponseEntity<?> validerDemande(AutorisationEnvListDechetDto request) throws Exception;

}
