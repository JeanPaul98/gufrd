package com.acl.formaliteagricultureapi.repository;

import com.acl.formaliteagricultureapi.models.DmdAutorisationAgrement;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
@Repository
public interface DemandeAutorisationAgrementRepository extends JpaRepository<DmdAutorisationAgrement, Long> {


    @Query(value = "select * from DMD_AUTORISATION_AGREMENT where etat=?1 " +
            "and compte_demandeur=?2 ", nativeQuery = true)
    List<DmdAutorisationAgrement>listDmdAutorisationAgrementByEtatAndUser(String etat, String user);

    @Query(value = "select d from DmdAutorisationAgrement d where d.etat=?1 ")
    List<DmdAutorisationAgrement>listDmdAutorisationAgrementByEtat(Etat etat);

    @Query(value = "select d from DmdAutorisationAgrement d where d.code = ?1")
    Optional<DmdAutorisationAgrement> findByCode(String code);
}
