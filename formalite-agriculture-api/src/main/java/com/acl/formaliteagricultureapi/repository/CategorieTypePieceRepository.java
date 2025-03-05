package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.CategorieTypePiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé on 02/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface CategorieTypePieceRepository extends JpaRepository<CategorieTypePiece,Long> {

    Optional<CategorieTypePiece>findByRef(String ref);

}
