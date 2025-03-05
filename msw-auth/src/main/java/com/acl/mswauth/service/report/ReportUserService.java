package com.acl.mswauth.service.report;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Service
public interface ReportUserService {

    ResponseEntity<byte[]> getUserReport();

    ResponseEntity<byte[]> getFormalitePhytoReport();
}
