package com.acl.formaliteagricultureapi.services.formalite;

import com.acl.formaliteagricultureapi.dto.formalite.RejetFormaliteDto;
import com.acl.formaliteagricultureapi.dto.formalite.UpdateFormaliteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author kol on 20/08/2024
 * @project formalite-agriculture-api
 */
@Service
public interface FormaliteService {

    ResponseEntity<?> soumettreDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>accepterDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>annulerDemandeAutorisation(UpdateFormaliteDto updateFormaliteDto);

    ResponseEntity<?>rejeterDemandeAutorisation(RejetFormaliteDto request);

    ResponseEntity<?>accepterDemandeFeedBack(UpdateFormaliteDto updateFormaliteDto) throws IOException;

    ResponseEntity<?>rejeterDemandeFeedback(RejetFormaliteDto request) throws IOException;

    ResponseEntity<?> confirmPaiement(UpdateFormaliteDto request) throws IOException;

    ResponseEntity<?> confirmPaiementPhyto(UpdateFormaliteDto request) throws IOException;

    ResponseEntity<?> confirmCertificat(UpdateFormaliteDto request) throws IOException;
}
