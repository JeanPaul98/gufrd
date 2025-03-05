package com.acl.vbs.repositories;

import com.acl.vbs.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<Site, Long> {


    Optional<Site> findByIdSite(Long idSite);

    @Query(value = "select s1_0.id_site,s1_0.concessionnaire,s1_0.nom_site,s1_0.redevance from site s1_0",nativeQuery = true)
    List<Site> list();
}