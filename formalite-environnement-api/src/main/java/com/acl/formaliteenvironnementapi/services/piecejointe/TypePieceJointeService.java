package com.acl.formaliteenvironnementapi.services.piecejointe;

import com.acl.formaliteenvironnementapi.dto.piecejointe.TypePieceJointeDto;
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
