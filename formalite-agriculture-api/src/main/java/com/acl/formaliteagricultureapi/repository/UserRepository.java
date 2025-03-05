package com.acl.formaliteagricultureapi.repository;


import com.acl.formaliteagricultureapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String username);

    Optional<User> findByLoginAndPassword(String username, String password);

//    @Query(value = "select d from User d where d.mswClient.compteClient = ?1")
//    List<User> findByCodeClient(String codeClient);


//    @Query(value = "select d from User d where d.mswStructure.id = ?1")
//    List<User> findByStructure(Long idStructure);


    @Query(value = "select d from User d where d.status = ?1")
    List<User> findByStatus(boolean status);

    @Query(value = "select d from User d where d.email = ?1")
    Optional<User> findByEmail(String email);
}

