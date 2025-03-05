package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.PieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long> {


    @Query(value = "select  d from PieceJointe d where d.formalite.id =?1")
    List<PieceJointe> findByFormalite(Long id);
}