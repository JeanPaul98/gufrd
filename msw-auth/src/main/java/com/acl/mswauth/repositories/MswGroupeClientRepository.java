package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswGroupeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MswGroupeClientRepository extends JpaRepository<MswGroupeClient, Long> {

    @Query(value = "select  d from MswGroupeClient d where  d.mswClient.id = ?1")
    Optional<MswGroupeClient> findByClient(Long idClient);
}