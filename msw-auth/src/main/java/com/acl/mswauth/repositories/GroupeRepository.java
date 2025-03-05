package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswGroupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  GroupeRepository extends JpaRepository<MswGroupe, Long> {
    Optional<MswGroupe> findByNomGroupe(String name);
    
    @Query(nativeQuery = true, value = "SELECT T1.* FROM MSW_GROUPES T1 inner join MSW_GROUPES_CLIENTS T2 ON T1.GROUPE_ID = T2.GROUPE_ID WHERE T2.CLIENT_ID = ? ")
    List<MswGroupe> findAllByClientId(Long clientId);
    
    @Query(nativeQuery = true, value = "SELECT T1.* FROM MSW_GROUPES T1 INNER JOIN MSW_GROUPES_CLIENTS T2 ON T1.GROUPE_ID = T2.GROUPE_ID INNER JOIN MSW_CLIENTS T3 ON T2.CLIENT_ID = T3.CLIENT_ID WHERE T3.CLIENT_COMPTE = ? ")
    List<MswGroupe> findAllByClientCompte(String clientCompte);
    
    
}
