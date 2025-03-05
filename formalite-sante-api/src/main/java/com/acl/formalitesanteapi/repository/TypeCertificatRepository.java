package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.TypeCertificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Repository
public interface TypeCertificatRepository extends JpaRepository<TypeCertificat, Long> {

    @Query(value = "select  d from TypeCertificat  d where d.ref = ?1")
    Optional<TypeCertificat> findByReference(String ref);
}
