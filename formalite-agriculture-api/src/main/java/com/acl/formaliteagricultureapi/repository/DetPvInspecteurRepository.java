package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetProcesVerbalInspecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetPvInspecteurRepository extends JpaRepository<DetProcesVerbalInspecteur, Long> {

    @Query(value = "select  d from DetProcesVerbalInspecteur  d where d.procesverbal.id = ?1")
    List<DetProcesVerbalInspecteur> findByProcesVerbal(Long id);
}
