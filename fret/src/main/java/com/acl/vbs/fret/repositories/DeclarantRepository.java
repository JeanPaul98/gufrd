package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Declarant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeclarantRepository extends JpaRepository<Declarant, Long> {
    Optional<Declarant> findByCompteClient(String compteClient);
}