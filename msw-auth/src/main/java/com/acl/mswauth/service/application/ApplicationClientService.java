package com.acl.mswauth.service.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/20/24
 * @project msw-auth
 */
@Service
public interface ApplicationClientService {

    ResponseEntity<?> getApplicationClients(String userlogin);

    ResponseEntity<?> getApplicationByCompteClient(String compte);
}
