package com.acl.mswauth.service.port;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/22/24
 * @project msw-auth
 */
@Service
public interface PortUserService {

    ResponseEntity<?> getPortUserClient();
}
