package com.acl.mswauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acl.mswauth.model.MswApplication;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<MswApplication, Long> {


@Query("select t from MswApplication t where t.reference =?1")
    Optional<MswApplication> findByReference(String name);

    @Query("select t from MswApplication t where t.name =?1")
    Optional<MswApplication> findByNameApplication(String name);
    
    @Query(nativeQuery = true, value = "SELECT  T1.*  FROM  USERMSW.APPLIS T1 INNER JOIN USERMSW.USERS_APPLIS T2 ON T1.APPLI_ID = T2.APPLI_ID INNER JOIN USERMSW.GROUPES T3 ON T2.GROUPE_ID = T3.GROUPE_ID  WHERE T3.GROUPE_NOM = ?1")
    List<MswApplication> findAllByGroupeName(String groupeName);
    
    @Query(nativeQuery = true, value = "SELECT  T1.*  FROM  USERMSW.APPLIS T1 INNER JOIN USERMSW.USERS_APPLIS T2 ON T1.APPLI_ID = T2.APPLI_ID  WHERE T2.GROUPE_ID = ?1  order by  T1.APPLI_ORDRE asc")
    List<MswApplication> findAllByGroupeId(Long groupeId);
    
}
