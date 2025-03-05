package com.acl.formaliteagricultureapi.services.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.DemandeAutorisationAgrementDto;
import com.acl.formaliteagricultureapi.dto.agrement.TraiterDemandeAutorisationAgrement;
import com.acl.formaliteagricultureapi.dto.agrement.UpdateDemandeAutorisationAgrement;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface DemandeAutorisationAgrementCreateService {
    ResponseEntity<?>createDemandeAutorisationAgrement(DemandeAutorisationAgrementDto agrementDto);

    ResponseEntity<?>soumettreDemandeAutorisationAgrement(UpdateDemandeAutorisationAgrement updateDemandeAutorisationAgrement);

    ResponseEntity<?>traiterDemandeAutorisationAgrement(TraiterDemandeAutorisationAgrement traiterDemandeAutorisationAgrement);

    ResponseEntity<?>createDemandeAutorisationAgrementSansPieceJointe(DemandeAutorisationAgrementDto demandeAutorisationAgrementDto);
}
