package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswGroupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acl.mswauth.model.MswUserApplication;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserApplicationRepository extends JpaRepository<MswUserApplication, Long> {

    @Query("select  d from  MswUserApplication  d where  d.mswApplication.id = ?1 and " +
            "d.groupe.id= ?2 and d.mswPort.code = ?3")
    Optional<MswUserApplication> findByInfosGroupe(Long idApplication, Long idGroupe, String codePort);

   @Query("select  d from MswUserApplication d where  d.groupe = ?1 order by d.mswApplication.name desc ")
    List<MswUserApplication> findByGroupe(MswGroupe mswGroupe);
}
