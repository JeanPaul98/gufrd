package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetAutorisationProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zansouyé
 */
@Repository
public interface DetAutorisationProduitRepository extends JpaRepository<DetAutorisationProduit, Long> {

    @Query(value = "select * from userfma.det_autorisation_produit f where f.id_autorisation = ?1", nativeQuery = true)
    List<DetAutorisationProduit> findByAutorisationId(Long idAutorisation);

    @Query(value = "select * from userfma.det_autorisation_produit f where f.id_autorisation = ?1 and id_produit=?2 ", nativeQuery = true)
    DetAutorisationProduit findByAutorisationIdAndProduitId(Long idAutorisation, Long idProduit);

    @Modifying
    @Query(value = "delete from userfma.det_autorisation_produit f where f.id_autorisation = ?1 ", nativeQuery=true)
    void deleteDetAutorisationProduitByIdAutorisation(Long idAutorisation);
}
