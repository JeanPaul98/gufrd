package com.acl.formalitesanteapi.repository;

import com.acl.formalitesanteapi.models.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Repository
public interface CertificatRepository extends JpaRepository<Certificat, Long> {
}
