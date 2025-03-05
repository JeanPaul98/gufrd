package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Agrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface AgrementRepository extends JpaRepository<Agrement, Long> {

}
