package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.TypeInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Repository
public interface TypeInspectionRepository extends JpaRepository<TypeInspection, Long> {

    @Query(value = "select  d from  TypeInspection  d where  d.ref = ?1")
    Optional<TypeInspection> findByRef(String ref);
}
