package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<MswClient, Long> {

    Optional<MswClient> findByCompteClient(String compteClient);

    @Query(value = "select  d from  MswClient d where  d.nif = ?1")
    Optional<MswClient> findClientByNif(String nif);

    @Query(value = "select  d from  MswClient d order by d.nomClient asc ")
    Page<MswClient> allCLients(Pageable pageable);

}
