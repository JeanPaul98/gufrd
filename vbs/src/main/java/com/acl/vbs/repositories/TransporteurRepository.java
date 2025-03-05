package com.acl.vbs.repositories;

import com.acl.vbs.entities.Transporteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporteurRepository extends JpaRepository<Transporteur, String> {
    Optional<Transporteur> findByCodeTransporteur(String codeTransporter);
}
