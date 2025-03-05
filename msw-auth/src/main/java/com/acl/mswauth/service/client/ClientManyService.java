package com.acl.mswauth.service.client;

import com.acl.mswauth.dto.ClientPortGroupeDto;
import com.acl.mswauth.dto.PortClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 16/09/2024
 * @project msw-auth
 */

public interface ClientManyService {

    ResponseEntity<?> getPortByClient(String codeClient);

    ResponseEntity<?> addClientToPort(PortClientDto request);


}
