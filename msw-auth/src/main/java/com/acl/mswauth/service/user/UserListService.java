package com.acl.mswauth.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/22/24
 * @project msw-auth
 */
@Service
public interface UserListService {

    ResponseEntity<?> getAllUserActiveList(int page, int size);

    ResponseEntity<?> findByIdUser(Long request);

    ResponseEntity<?> findByProfil(String currentUserName);

    ResponseEntity<?> getAllActiveList();
}
