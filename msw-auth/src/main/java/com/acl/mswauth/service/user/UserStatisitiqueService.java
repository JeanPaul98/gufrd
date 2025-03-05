package com.acl.mswauth.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Service
public interface UserStatisitiqueService {

    ResponseEntity<?> getUserStatisitique();
}
