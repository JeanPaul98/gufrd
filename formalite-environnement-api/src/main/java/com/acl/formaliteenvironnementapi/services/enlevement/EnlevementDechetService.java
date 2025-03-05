package com.acl.formaliteenvironnementapi.services.enlevement;

import com.acl.formaliteenvironnementapi.dto.autorisation.AutorisationEnvDechetDto;
import com.acl.formaliteenvironnementapi.dto.enlevement.EnlevementDechetDto;
import com.acl.formaliteenvironnementapi.dto.enlevement.EnlevementListDechetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 12/09/2024
 * @project formalite-environnement-api
 */
@Service
public interface EnlevementDechetService {

    ResponseEntity<?> create(EnlevementDechetDto request);

    ResponseEntity<?> validerDemande(EnlevementListDechetDto request) throws Exception;


}
