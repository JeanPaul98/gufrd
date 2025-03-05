package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetPvProduitRepository extends JpaRepository<DetProcesVerbalProduit, Long> {
    @Query(value = "select  d from DetProcesVerbalProduit  d where  d.procesVerbal.id = ?1")
    List<DetProcesVerbalProduit> findByProcesVerbal(Long id);
}
