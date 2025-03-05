package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.CategorieTypePiece;
import com.acl.formaliteagricultureapi.models.DetCategorieTypePiece;
import com.acl.formaliteagricultureapi.models.TypePieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetCategorieTypePieceRepository extends JpaRepository<DetCategorieTypePiece, Long> {
    Optional<DetCategorieTypePiece> findByCategorieTypePieceAndTypePieceJointe(CategorieTypePiece categorieTypePiece, TypePieceJointe typePieceJointe);

    @Query("SELECT d FROM DetCategorieTypePiece d WHERE d.categorieTypePiece.ref = :ref")
    List<DetCategorieTypePiece> findByCategorieTypePieceRef(@Param("ref") String ref);

}