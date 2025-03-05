package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acl.mswauth.model.MswPortClient;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortClientRepository extends JpaRepository<MswPortClient, Long> {
    @Query(value = "select t from MswPortClient t where t.mswClient.compteClient = ?1 ")
    List<MswPortClient> findByClient(String compteClient);

    @Query(value = "select  d from MswPortClient  d where d.mswStructure.id = ?1")
    List<MswPortClient> findByStructureId(Long id);
}
