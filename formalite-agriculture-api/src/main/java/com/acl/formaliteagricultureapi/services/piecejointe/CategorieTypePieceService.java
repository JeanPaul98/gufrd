package com.acl.formaliteagricultureapi.services.piecejointe;

import com.acl.formaliteagricultureapi.dto.piecejointe.CategorieTypePieceDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Zansouyé on 01/09/2024
 * @project formalite-agriculture-api
 */
@Service
public interface CategorieTypePieceService {

    ResponseEntity<?>create(CategorieTypePieceDto categorieTypePieceDto);

    ResponseEntity<?>listCategories();

    ResponseEntity<?> updateCategories(CategorieTypePieceDto categorieTypePieceDto, long id);

}
