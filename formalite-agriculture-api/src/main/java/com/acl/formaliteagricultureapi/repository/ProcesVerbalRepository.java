package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.ProcesVerbal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface ProcesVerbalRepository extends JpaRepository<ProcesVerbal , Long> {

    @Query(value = "select d from ProcesVerbal d where  d.typePv.ref = ?1")
    List<ProcesVerbal> findByTypeRef(String ref);

    @Query(value = "select  d from ProcesVerbal  d where  d.formalite.id = ?1")
    List<ProcesVerbal> findByFormaliteId(Long id);

    @Query(value = "select  d from ProcesVerbal  d where  d.formalite.id = ?1")
    Optional<ProcesVerbal> findByFormalite(Long id);
}
