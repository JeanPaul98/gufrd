package com.acl.mswauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acl.mswauth.model.MswPortAppli;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortAppliRepository extends JpaRepository<MswPortAppli, Long> {

    @Query(value = "select t from MswPortAppli t where t.mswApplication.id = ?1")
    Optional<MswPortAppli> findByApplication(long applid);

    @Query(value = "select t from MswPortAppli t where t.mswPort.code = ?1")
    List<MswPortAppli> findByPort(String port);
}
