package com.acl.formaliteagricultureapi.services.agrement;

import com.acl.formaliteagricultureapi.dto.agrement.AgrementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface AgrementService {

    ResponseEntity<?> createAgrement(AgrementDto typeAgrementDto);

    ResponseEntity<?> findByNumeroAgrement(String numero);
}
