package com.acl.mswauth.service.groupe;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Service
public interface GroupeManyService {

    ResponseEntity<?> getAllGroupes();


}
