package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.TypeAutorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Repository
public interface TypeAutorisationRepository extends JpaRepository<TypeAutorisation, Long> {

    @Query(value = "select  d from TypeAutorisation  d where  d.ref = ?1")
    Optional<TypeAutorisation> findByRef(String ref);
}
