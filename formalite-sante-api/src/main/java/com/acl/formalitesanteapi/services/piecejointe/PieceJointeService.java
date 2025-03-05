package com.acl.formalitesanteapi.services.piecejointe;

import com.acl.formalitesanteapi.dto.piecejointe.FormalitePieceJointeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@Service
public interface PieceJointeService {

    ResponseEntity<?> ajoutFile(FormalitePieceJointeDto request) throws JsonProcessingException;
    ResponseEntity<?> listePieceJointe(Long request);
}
