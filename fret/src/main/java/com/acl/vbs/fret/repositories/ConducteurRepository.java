package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Conducteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
}