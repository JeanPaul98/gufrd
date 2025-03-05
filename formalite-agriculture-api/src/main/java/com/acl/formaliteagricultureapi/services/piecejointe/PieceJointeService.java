package com.acl.formaliteagricultureapi.services.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.FormalitePieceJointeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 02/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface PieceJointeService {
    ResponseEntity<?> ajoutFile(FormalitePieceJointeDto request) throws JsonProcessingException;

    ResponseEntity<?> listePieceJointe(Long request);


}
