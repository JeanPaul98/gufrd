package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Repository
public interface StructureRepository  extends JpaRepository<Structure, Long> {
    Optional<Structure> findByCode(String code);

}
