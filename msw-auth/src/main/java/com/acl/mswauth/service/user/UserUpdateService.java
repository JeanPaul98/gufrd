package com.acl.mswauth.service.user;

import com.acl.mswauth.dto.register.RegisterDto;
import com.acl.mswauth.dto.register.RegisterUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/24/24
 * @project msw-auth
 */
@Service
public interface UserUpdateService {

    ResponseEntity<?> update(RegisterUpdateDto request);

    ResponseEntity<?> updateProfil(RegisterUpdateDto request);
}
