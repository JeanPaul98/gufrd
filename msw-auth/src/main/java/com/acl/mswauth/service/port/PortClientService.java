package com.acl.mswauth.service.port;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PortClientService {

    ResponseEntity<?> addPortClients(String compteClient, String ports );


}
