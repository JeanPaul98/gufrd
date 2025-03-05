package com.acl.mswauth.repositories;

import com.acl.mswauth.model.MswLoggedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Repository
public interface MswLoggedUserRepository extends JpaRepository<MswLoggedUser, Long> {
}
