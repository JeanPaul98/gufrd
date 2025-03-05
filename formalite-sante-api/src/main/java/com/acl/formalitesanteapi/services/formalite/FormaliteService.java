package com.acl.formalitesanteapi.services.formalite;


import com.acl.formalitesanteapi.dto.formalite.RejetFormaliteDto;
import com.acl.formalitesanteapi.dto.formalite.UpdateFormaliteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface FormaliteService {

    ResponseEntity<?> soumettreDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>accepterDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>annulerDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>rejeterDemande(RejetFormaliteDto request);

}
