package com.acl.escalenavire.repositories;

import com.acl.escalenavire.models.AnnonceEscale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnnonceEscaleRepository extends JpaRepository<AnnonceEscale, Long> {

    Optional<AnnonceEscale> findByNumeroAan(String numeroAan);

    @Query(value="SELECT ae1_0.id_ae1_0.adresse_affreteur_arrivee,ae1_0.numero_voyage,ae1_0.objet_escale,ae1_0.id_navire,\n" +
            "ae1_0.nom_affreteur_arrivee,ae1_0.nom_affreteur_depart,ae1_0.nom_cdt,ae1_0.nombre_total_passager_debarque,ae1_0.port_destination,\n" +
            "ae1_0.port_provenance,ae1_0.propulseur_babord_ok,\n " +
            " FROM ae1_0 order by ae1_0.id_annonce_escale desc", nativeQuery = true)
    Page<AnnonceEscale> AllAnnonce(Pageable pageable);

    Optional<AnnonceEscale> findByNomAffreteurArrivee(String affreteurArrivee);

    Optional<AnnonceEscale> findById(Long id);


}