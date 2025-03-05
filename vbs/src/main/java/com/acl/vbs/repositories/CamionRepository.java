package com.acl.vbs.repositories;

import com.acl.vbs.entities.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author Elikplim 14/11/2024
 */
public interface CamionRepository extends JpaRepository<Camion, String> {
    Optional<Camion> findByImmatriculation(String registration);
}
