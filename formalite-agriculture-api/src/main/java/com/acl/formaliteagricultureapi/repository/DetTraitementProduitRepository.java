package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DetPhytosanitaireProduit;
import com.acl.formaliteagricultureapi.models.DetTraitementProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DetTraitementProduitRepository extends JpaRepository<DetTraitementProduit, Long> {

    @Query(value = "select * from userfma.det_traitement_produit f where f.ID_PHYTOSANITAIRE = ?1", nativeQuery = true)
    List<DetTraitementProduit> findByPhytosanitaire(Long idPhytosanitaire);

    @Query(value = "select * from userfma.det_traitement_produit f where f.ID_PHYTOSANITAIRE = ?1 and id_produit=?2 ", nativeQuery = true)
    DetTraitementProduit findByPhytosanitaireIdAndProduitId(Long idPhytosanitaire, Long idProduit);


    @Query(value = "delete from userfma.det_traitement_produit f where f.id_phytosanitaire = ?1 ", nativeQuery=true)
    void deleteDetAutorisationProduitByIdPhytosanitaire(Long idPhytosanitaire);

}
