package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.PieceJointe;
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
public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long> {

    @Query(value = "select  d from PieceJointe d where d.formalite.id =?1")
    List<PieceJointe> findByFormalite(Long id);
}
