package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CamionRepository extends JpaRepository<Camion, Long> {
    Optional<Camion> findByImmatriculation(String immatriculation);
}