package com.acl.mswauth.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/24/24
 * @project msw-auth
 */
@Service
public interface UserSearchService {

    ResponseEntity<?> getSearchResult(String keyword,  int page, int size);

}
