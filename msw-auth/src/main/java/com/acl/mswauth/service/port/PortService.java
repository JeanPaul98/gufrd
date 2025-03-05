package com.acl.mswauth.service.port;


import com.acl.mswauth.request.PortAppliRequest;
import com.acl.mswauth.request.PortClientRequest;
import com.acl.mswauth.dto.PortDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PortService {
    ResponseEntity<?> create(PortDto request);
    ResponseEntity<?> getAllPorts();
    ResponseEntity<?> addApplication(PortAppliRequest request);
    ResponseEntity<?> addClient(PortClientRequest request);

    ResponseEntity<?> getAllApplicationByPort(String port);

    ResponseEntity<?> getPortClient(String compteClient);

    ResponseEntity<?> getPortPays(String codePays);

}
