package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Chargeur;
import com.acl.vbs.fret.entities.Parametrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametrageRepository extends JpaRepository<Parametrage, Long> {
    Optional<Parametrage> findByChargeurId(Long chargeurId);
}