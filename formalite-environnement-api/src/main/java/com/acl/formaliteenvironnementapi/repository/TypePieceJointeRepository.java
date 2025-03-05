package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.TypePieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TypePieceJointeRepository extends JpaRepository<TypePieceJointe, Long> {


    Optional<TypePieceJointe> findById(Long id);

    Optional<TypePieceJointe>findByLibelle(String libelle);

//    @Query(value = "select d from  TypePieceJointe d where d.categorieTypePiece.ref = ?1")
//    List<TypePieceJointe> findAllByCategoryPiece(String ref);
}