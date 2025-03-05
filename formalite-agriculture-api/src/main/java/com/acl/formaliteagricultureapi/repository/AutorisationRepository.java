package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé
 */
@Repository
public interface AutorisationRepository extends JpaRepository<Autorisation, Long> {

    Optional<Autorisation> findById(Long id);

}
