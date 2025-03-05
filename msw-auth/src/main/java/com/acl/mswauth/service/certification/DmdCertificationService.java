package com.acl.mswauth.service.certification;

import com.acl.mswauth.dto.certification.DmdCertificationDto;
import com.acl.mswauth.dto.certification.VerifyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Service
public interface DmdCertificationService {

     ResponseEntity<?> insert(DmdCertificationDto request);

    ResponseEntity<?> verify(VerifyDto verifyDto);
}
