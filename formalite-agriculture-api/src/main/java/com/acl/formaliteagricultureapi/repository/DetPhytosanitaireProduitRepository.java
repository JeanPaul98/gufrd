package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetPhytosanitaireProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetPhytosanitaireProduitRepository extends JpaRepository<DetPhytosanitaireProduit, Long> {


    @Query(value = "select * from userfma.det_phytosanitaire_produit f where f.ID_PHYTOSANITAIRE = ?1", nativeQuery = true)
    List<DetPhytosanitaireProduit> findByPhytosanitaire(Long idPhytosanitaire);

    @Query(value = "select * from userfma.det_phytosanitaire_produit f where f.ID_PHYTOSANITAIRE = ?1 and id_produit=?2 ", nativeQuery = true)
    DetPhytosanitaireProduit findByPhytosanitaireIdAndProduitId(Long idPhytosanitaire, Long idProduit);


    @Modifying
    @Query(value = "delete from userfma.det_phytosanitaire_produit f where f.ID_PHYTOSANITAIRE = ?1 ", nativeQuery=true)
    void deleteDetAutorisationProduitByIdPhytosanitaire(Long idPhytosanitaire);

}
