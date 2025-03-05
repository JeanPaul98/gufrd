package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypeSociete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeSocieteRepository extends JpaRepository<TypeSociete, Long> {

    @Query(value = "select  d from  TypeSociete d where d.ref = ?1 ")
    Optional<TypeSociete> findByReference(String reference);
}
