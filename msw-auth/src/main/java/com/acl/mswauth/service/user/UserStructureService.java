package com.acl.mswauth.service.user;

import com.acl.mswauth.dto.register.RegisterStructureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/8/24
 * @project msw-auth
 */
@Service
public interface UserStructureService {

     ResponseEntity<?> insert(RegisterStructureDto request);
}
