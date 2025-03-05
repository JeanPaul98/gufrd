package com.acl.formalitesanteapi.services.piecejointe;

import com.acl.formalitesanteapi.dto.piecejointe.CategorieTypePieceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
@Service
public interface CategorieTypePieceService {

    ResponseEntity<?>create(CategorieTypePieceDto categorieTypePieceDto);

    ResponseEntity<?>listCategories();

    ResponseEntity<?> updateCategories(CategorieTypePieceDto categorieTypePieceDto, long id);

}
