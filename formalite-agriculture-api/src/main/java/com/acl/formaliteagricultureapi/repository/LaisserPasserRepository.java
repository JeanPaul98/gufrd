package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.LaisserPasser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface LaisserPasserRepository extends JpaRepository<LaisserPasser, Long> {

    @Query(value = "select * from laisser_passer where formalite_id=?1 ", nativeQuery = true)
    LaisserPasser findByIdFormalite(Long idFormalite);
}