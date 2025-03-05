package com.acl.mswauth.service.certification;

import com.acl.mswauth.dto.certification.DmdCertificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/11/24
 * @project msw-auth
 */
@Service
public interface OpertateurCertificationService {

    ResponseEntity<?> insert(DmdCertificationDto request);
}
