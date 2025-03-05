package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    Optional<Structure> findByCode(String code);
}
