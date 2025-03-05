package com.acl.vbs.repositories;

import com.acl.vbs.entities.AireStockage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AireStockageRepository extends JpaRepository<AireStockage, String> {
    Optional<AireStockage> findByCodeAireStockage(String codeAireStockage);
}