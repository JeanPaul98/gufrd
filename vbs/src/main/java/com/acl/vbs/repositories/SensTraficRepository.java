package com.acl.vbs.repositories;

import com.acl.vbs.entities.SensTrafic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SensTraficRepository extends JpaRepository<SensTrafic, String> {
    Optional<SensTrafic> findByCodeSensTrafic(String codeSensTrafic);

    @Query(value = "select  s.CODE_SENS_TRAFIC,s.LIB_SENS_TRAFIC from SIPEDBA.SENS_TRAFIC s", nativeQuery = true)
    List<SensTrafic> list();
}