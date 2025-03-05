package com.acl.formaliteenvironnementapi.services.formalite;



import com.acl.formaliteenvironnementapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteenvironnementapi.dto.formalite.UpdateFormaliteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 20/08/2024
 */
@Service
public interface FormaliteService {

    ResponseEntity<?> soumettreDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>accepterDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>annulerDemande(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>rejeterDemande(RejetFormaliteDto request);

}
