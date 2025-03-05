package com.acl.mswauth.service.user;

import com.acl.mswauth.dto.register.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Service
public interface UserClientService {

    ResponseEntity<?> insert(RegisterDto request);

}
