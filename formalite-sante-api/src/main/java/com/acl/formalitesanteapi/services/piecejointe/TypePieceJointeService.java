package com.acl.formalitesanteapi.services.piecejointe;

import com.acl.formalitesanteapi.dto.piecejointe.TypePieceJointeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@Service
public interface TypePieceJointeService {

    ResponseEntity<?> create(TypePieceJointeDto typePieceJointeDto);

    ResponseEntity<?> list();

    ResponseEntity<?> findTypeByCategory(String ref);

    ResponseEntity<?> deleteTypePieceJointe(long id);

    ResponseEntity<?> updateTypePieceJointe(TypePieceJointeDto typePieceJointeDto, long id);

}
