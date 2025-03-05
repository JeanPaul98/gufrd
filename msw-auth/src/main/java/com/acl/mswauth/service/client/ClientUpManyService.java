package com.acl.mswauth.service.client;

import com.acl.mswauth.dto.ClientPortGroupeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Service
public interface ClientUpManyService {

    ResponseEntity<?> addClientGroupePort(ClientPortGroupeDto request);

}
