package com.acl.mswauth.repositories;

import com.acl.mswauth.model.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Repository
public interface UserClientRepository extends JpaRepository<UserClient, Long> {
    @Query(value = "select  d from UserClient d where d.user.id = ?1")
    List<UserClient> findByUserId(Long id);

    @Query(value = "select  d from UserClient d where d.user.login=?1")
    List<UserClient> findByClientLogin(String login);
}
