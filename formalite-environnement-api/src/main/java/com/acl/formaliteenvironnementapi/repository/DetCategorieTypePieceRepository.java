package com.acl.formaliteenvironnementapi.repository;


import com.acl.formaliteenvironnementapi.models.CategorieTypePiece;
import com.acl.formaliteenvironnementapi.models.DetCategorieTypePiece;
import com.acl.formaliteenvironnementapi.models.TypePieceJointe;
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