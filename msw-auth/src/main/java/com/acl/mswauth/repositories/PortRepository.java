package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswPortClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acl.mswauth.model.MswPort;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortRepository extends JpaRepository<MswPort, Long> {

    @Query(value = "select d from MswPort d where d.code = ?1")
    Optional<MswPort> findByCode(String codePort);
    @Query(value = "select d from MswPort d where d.mswPays.code = ?1")
    List<MswPort> findByCodePays(String code);
}
