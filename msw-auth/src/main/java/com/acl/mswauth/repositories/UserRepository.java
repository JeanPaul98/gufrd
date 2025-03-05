package com.acl.mswauth.repositories;


import com.acl.mswauth.interfaces.StatistiqueUserGroupeInterface;
import com.acl.mswauth.interfaces.UserInterface;
import com.acl.mswauth.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	Optional<User> findByLogin(String username);

    Optional<User> findByLoginAndPassword(String username, String password);

    @Query(value = "select d from User d where d.mswClient.compteClient = ?1")
    List<User> findByCodeClient(String codeClient);
    
    
    @Query(value = "select d from User d where d.mswStructure.id = ?1")
    List<User> findByStructure(Long idStructure);

    
    @Query(value = "select d from User d where d.status = ?1")
    List<User> findByStatus(boolean status);

    @Query(value = "select d from User d where d.email = ?1")
    Optional<User> findByEmail(String email);

    @Query(value = "select  distinct(c3.CLIENT_NOM ) as nomEntreprise from USERMSW.USERS d, \n" +
            "USERMSW.USERS_CLIENTS C2, USERMSW.CLIENTS C3 where\n" +
            "    C2.CLIENT_ID = c3.CLIENT_ID and d.USER_STATUS = 1", nativeQuery = true)
    List<UserInterface>  findListEntreprise();

    @Query(value = "select  distinct(c5.GROUPE_NOM ) as nomGroupe, COUNT(distinct c3.CLIENT_NOM) as nombre from USERMSW.USERS d,\n" +
            "            USERMSW.USERS_CLIENTS C2, USERMSW.CLIENTS C3 , USERMSW.GROUPES_CLIENTS c4 , USERMSW.GROUPES c5 where\n" +
            "               C2.CLIENT_ID = c3.CLIENT_ID and d.USER_STATUS = 1 and C4.CLIENT_ID = C2.CLIENT_ID\n " +
            "and d.USER_ID = c2.USER_ID " +
            "and C4.GROUPE_ID = c5.GROUPE_ID group by c5.GROUPE_NOM ", nativeQuery = true)
    List<StatistiqueUserGroupeInterface>  findListStatisqueGroupe();


    @Query(value = "select  d from User  d where  d.mswStructure.id = ?1 and d.email = ?2")
    Optional<User> findOneStructure(Long idStructure, String email);

    Page<User> findByStatus(boolean status, Pageable pageable);

    @Query(value = "select d.USER_ID as idUser, c3.CLIENT_NOM as nomEntreprise , d.USER_FULLNAME as fullname, d.USER_LOGIN as login,\n" +
            "        c2.USER_MOBILE as telephone, c2.USER_MAIL as email, c2.USER_FONCTION as fonction, c5.GROUPE_NOM as groupe\n" +
            "from USERMSW.USERS d,\n" +
            "USERMSW.USERS_CLIENTS C2, USERMSW.CLIENTS C3, USERMSW.GROUPES_CLIENTS c4, USERMSW.GROUPES c5 where\n" +
            "    C2.CLIENT_ID = c3.CLIENT_ID\n" +
            " and d.USER_STATUS = 1 and d.USER_ID = C2.USER_ID\n" +
            "and c2.CLIENT_ID = c4.CLIENT_ID and c4.GROUPE_ID = c5.GROUPE_ID order by c3.CLIENT_NOM asc", nativeQuery = true)
    Page<UserInterface> findAllUsersActive(Pageable pageable);

    @Query(value = "select d.USER_ID as idUser, c3.CLIENT_NOM as nomEntreprise , d.USER_FULLNAME as fullname, d.USER_LOGIN as login,\n" +
            "        c2.USER_MOBILE as telephone, c2.USER_MAIL as email, c2.USER_FONCTION as fonction, c5.GROUPE_NOM as groupe, \n " +
            "c3.CLIENT_COMPTE as compteClient " +
            "from USERMSW.USERS d,\n" +
            "USERMSW.USERS_CLIENTS C2, USERMSW.CLIENTS C3, USERMSW.GROUPES_CLIENTS c4, USERMSW.GROUPES c5 where\n" +
            "    C2.CLIENT_ID = c3.CLIENT_ID\n" +
            " and d.USER_STATUS = 1 and d.USER_ID = C2.USER_ID\n" +
            "and c2.CLIENT_ID = c4.CLIENT_ID and c4.GROUPE_ID = c5.GROUPE_ID order by c5.GROUPE_NOM asc", nativeQuery = true)
    List<UserInterface> findAllListUsersActive();
}

