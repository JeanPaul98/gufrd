package com.acl.vbs.repositories;

import com.acl.vbs.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaysRepository extends JpaRepository<Pays, String> {
    Optional<Pays> findByCodePays(String codePays);
}
