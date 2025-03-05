package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypePv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypePvRepository extends JpaRepository<TypePv, Long> {

    Optional<TypePv> findByRef(String ref);

}
