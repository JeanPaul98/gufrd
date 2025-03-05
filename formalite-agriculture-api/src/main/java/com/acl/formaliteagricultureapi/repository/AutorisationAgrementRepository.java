package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.AutorisationAgrement;
import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé on 03/10/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface AutorisationAgrementRepository extends JpaRepository<AutorisationAgrement, Long> {

    Optional<AutorisationAgrement>findByNumero(String numero);

    @Query(value = "select  d from AutorisationAgrement  d where d.dmdAutorisationAgrement.code = ?1")
    Optional<AutorisationAgrement> findByCodeDmande(String code);
}
