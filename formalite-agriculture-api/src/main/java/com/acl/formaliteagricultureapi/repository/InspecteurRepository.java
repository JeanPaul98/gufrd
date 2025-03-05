package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Inspecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 26/08/2024
 * @project formalite-agriculture-api
 */

@Repository
public interface InspecteurRepository extends JpaRepository<Inspecteur, Long> {
}
