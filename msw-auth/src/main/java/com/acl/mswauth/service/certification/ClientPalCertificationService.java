package com.acl.mswauth.service.certification;

import com.acl.mswauth.dto.certification.DmdCertificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Service
public interface ClientPalCertificationService {

    ResponseEntity<?> insert(DmdCertificationDto request);
}
