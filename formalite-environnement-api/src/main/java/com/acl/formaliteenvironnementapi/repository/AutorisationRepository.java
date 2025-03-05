package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.Autorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Repository
public interface AutorisationRepository  extends JpaRepository<Autorisation, Long> {
}
