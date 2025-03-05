package com.acl.formaliteenvironnementapi.services.piecejointe;

import com.acl.formaliteenvironnementapi.dto.piecejointe.DetCategorieTypeJointeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DetCategorieTypePieceService {

    ResponseEntity<?> listDetCategorieTypePiece();

    ResponseEntity<?> createDetCategorieTypePiece(DetCategorieTypeJointeDto detCategorieTypeJointeDto);

    ResponseEntity<?>  getDetCategorieTypePieceByRef(String ref);

}
