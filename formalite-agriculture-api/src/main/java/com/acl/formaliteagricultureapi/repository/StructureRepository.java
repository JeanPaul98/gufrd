package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    Optional<Structure>findByCode(String code);
}
