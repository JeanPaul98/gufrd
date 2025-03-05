package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.TypeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface TypeProduitRepository extends JpaRepository<TypeProduit, Long> {
}
