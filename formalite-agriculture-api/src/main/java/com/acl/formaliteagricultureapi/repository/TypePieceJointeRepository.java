package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypePieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé on 30/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface TypePieceJointeRepository extends JpaRepository<TypePieceJointe, Long> {

    Optional<TypePieceJointe>findById(Long id);

    Optional<TypePieceJointe>findByLibelle(String libelle);

//    @Query(value = "select d from  TypePieceJointe d where d.categorieTypePiece.ref = ?1")
//    List<TypePieceJointe> findAllByCategoryPiece(String ref);
}
