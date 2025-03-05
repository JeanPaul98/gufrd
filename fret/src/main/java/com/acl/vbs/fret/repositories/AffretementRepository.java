package com.acl.vbs.fret.repositories;

import com.acl.vbs.fret.entities.Affretement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffretementRepository extends JpaRepository<Affretement, Long> {
}