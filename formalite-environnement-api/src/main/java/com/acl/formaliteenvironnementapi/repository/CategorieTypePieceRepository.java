package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.CategorieTypePiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieTypePieceRepository extends JpaRepository<CategorieTypePiece, Long> {
    Optional<CategorieTypePiece> findByRef(String ref);

}