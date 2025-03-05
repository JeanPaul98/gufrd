package com.acl.formaliteagricultureapi.services.processVerval.cargaison;

import com.acl.formaliteagricultureapi.dto.procesVerbal.PvPhytoSanitaireCargConteneurDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 07/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PvPhytoCargaisonService {

    ResponseEntity<?> create(PvPhytoSanitaireCargConteneurDto request);
}
