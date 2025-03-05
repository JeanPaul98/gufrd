package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Transporteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransporteurRepository extends JpaRepository<Transporteur, Long> {
    Optional<Transporteur> findByRaisonSociale(String raisonSociale);
}