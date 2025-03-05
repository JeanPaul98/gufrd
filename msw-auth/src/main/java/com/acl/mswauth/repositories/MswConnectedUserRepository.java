package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswConnectedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MswConnectedUserRepository extends JpaRepository<MswConnectedUser, Long> {

    @Query("select d from  MswConnectedUser d  where d.userLogin =?1")
    Optional<MswConnectedUser> findByUserLogin(String login);

    @Query("select d from MswConnectedUser d where d.sessionId = ?1")
    Optional<MswConnectedUser> findBySession(String userId);
}
