package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypeCertificat;
import com.acl.formaliteagricultureapi.models.TypeInspPhyto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface TypeCertificatRepository extends JpaRepository<TypeCertificat, Long> {

    Optional<TypeCertificat> findByRef(String rf);
}
