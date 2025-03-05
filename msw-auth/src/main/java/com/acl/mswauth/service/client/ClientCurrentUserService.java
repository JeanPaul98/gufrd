package com.acl.mswauth.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/29/24
 * @project msw-auth
 */
@Service
public interface ClientCurrentUserService {

    ResponseEntity<?> getAllClientByUser();
}
