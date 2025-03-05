package com.acl.mswauth.service.client;

import org.springframework.http.ResponseEntity;

/**
 * @author kol on 11/28/24
 * @project msw-auth
 */

public interface ClientSearchService {
    ResponseEntity<?> getSearchResult(String keyword, int page, int size);
}
