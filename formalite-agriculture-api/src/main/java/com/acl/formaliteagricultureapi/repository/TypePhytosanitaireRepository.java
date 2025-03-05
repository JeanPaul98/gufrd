package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypeInspPhyto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface TypePhytosanitaireRepository extends JpaRepository<TypeInspPhyto, Long> {

    Optional<TypeInspPhyto> findByRef(String ref);
}
