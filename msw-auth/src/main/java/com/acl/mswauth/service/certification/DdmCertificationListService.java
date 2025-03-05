package com.acl.mswauth.service.certification;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/11/24
 * @project msw-auth
 */
@Service
public interface DdmCertificationListService {

    ResponseEntity<?> getAllCertificationList(int page, int size);
}
