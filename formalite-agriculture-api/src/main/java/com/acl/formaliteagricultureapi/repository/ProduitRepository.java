package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Zansouyé
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    @Query(value = "select  d from Produit  d where d.libelle = ?1")
    Optional<Produit> findByLibelle(String libelle);

    Optional<Produit> findByCode(String code);

    List<Produit> findByRef(String ref);
}
