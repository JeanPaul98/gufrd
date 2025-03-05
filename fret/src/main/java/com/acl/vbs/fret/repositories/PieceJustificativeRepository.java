package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.PieceJustificative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieceJustificativeRepository extends JpaRepository<PieceJustificative, Long> {
    //    @Query(value = "SELECT * from type_piece t, piece_justificative p where p.id_type_piece = t.id and p.id_declaration_fret =?1", nativeQuery = true)
    List<PieceJustificative> findAllByDeclarationFretId(Long Id);
}