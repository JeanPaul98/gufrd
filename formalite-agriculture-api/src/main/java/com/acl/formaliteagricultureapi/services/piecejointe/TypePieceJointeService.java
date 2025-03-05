package com.acl.formaliteagricultureapi.services.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 01/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface TypePieceJointeService {

    ResponseEntity<?> create(TypePieceJointeDto typePieceJointeDto);

    ResponseEntity<?> list();

//    ResponseEntity<?> findTypeByCategory(String ref);

    ResponseEntity<?> deleteTypePieceJointe(long id);

    ResponseEntity<?> updateTypePieceJointe(TypePieceJointeDto typePieceJointeDto, long id);
 }
