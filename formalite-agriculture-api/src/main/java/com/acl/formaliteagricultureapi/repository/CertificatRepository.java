package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface CertificatRepository extends JpaRepository<Certificat, Long> {
}
