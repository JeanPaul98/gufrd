package com.acl.mswauth.repositories;

import com.acl.mswauth.model.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
    Optional<Societe> findByNif(String nif);
}