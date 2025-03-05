package com.acl.mswauth.repositories;

import com.acl.mswauth.model.DemandeCertification;
import com.acl.mswauth.model.enumeration.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Repository
public interface DemandeCertificationRepository extends JpaRepository<DemandeCertification, Long> {


    @Query(value = "select  d from DemandeCertification  d where  d.code = ?1 and d.nif = ?2")
    Optional<DemandeCertification> findByCodeValide(String code, String nif);

    @Query(value = "select  d from  DemandeCertification  d where  d.statut = ?1")
    Page<DemandeCertification> getDemandeCertificationByEtat(Statut etat, Pageable pageable);
}
