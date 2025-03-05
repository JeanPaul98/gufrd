package com.acl.vbs.repositories;

import com.acl.vbs.entities.Transitaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransitaireRepository extends JpaRepository<Transitaire, Long> {
    Optional<Transitaire> findByClientCompteClient(String compteClient);
}