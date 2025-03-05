package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswGroupeClient;
import com.acl.mswauth.model.MswStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupeClientRepository extends JpaRepository<MswGroupeClient, Long> {

    @Query("select t from MswGroupeClient t where t.mswClient.compteClient = ?1")
   Optional<MswGroupeClient> findByClient(String compteClient);

    @Query("select t from MswGroupeClient t where t.mswStructre.nom = ?1")
    Optional<MswGroupeClient> findByStructure(String nomStructure);

    @Query("select t from MswGroupeClient t where t.mswStructre.id = ?1")
    Optional<MswGroupeClient> findByStructureId(Long nomStructure);

    @Query("select t from MswGroupeClient t where t.mswClient.compteClient = ?1")
    List<MswGroupeClient> findByClientCompte(String compteClient);

}
