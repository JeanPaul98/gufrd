package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Chargeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChargeurRepository extends JpaRepository<Chargeur, Long> {
    Optional<Chargeur> findByNom(String nom);

    Optional<Chargeur> findByCompteClient(String compteClient);
}