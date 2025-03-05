package com.acl.formaliteenvironnementapi.repository;

import com.acl.formaliteenvironnementapi.models.TypeDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Repository
public interface TypeDeclarationRepository extends JpaRepository<TypeDeclaration, Long> {
}
