package com.acl.formaliteagricultureapi.services.processVerval.navire;

import com.acl.formaliteagricultureapi.dto.procesVerbal.navire.PvPhytoSanitaireNavireDto;
import org.springframework.http.ResponseEntity;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
public interface PvPhytoNavireService {

    ResponseEntity<?> create(PvPhytoSanitaireNavireDto request);

}
