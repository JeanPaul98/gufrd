package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.VarieteProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarieteProduitRepository extends JpaRepository<VarieteProduit, Long> {
}