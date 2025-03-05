package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {


    @Query(value = "select d from Societe d where d.raisonSociale = ?1")
    Optional<Societe> findbyRaisonSociale(String  raisonSocial);

    //@Query(value = "select  d from Societe  d where d.nif= ?1")
    Optional<Societe> findByNif(String nif);
}
