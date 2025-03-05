package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypeAutorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé
 */
@Repository
public interface TypeAutorisationRepository extends JpaRepository<TypeAutorisation, Long> {

    Optional<TypeAutorisation>findByRef(String ref);
}
