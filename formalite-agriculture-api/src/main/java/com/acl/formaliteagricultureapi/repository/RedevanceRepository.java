package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Redevance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 29/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface RedevanceRepository extends JpaRepository<Redevance, Long> {
}
