package com.acl.mswauth.service.client;

import com.acl.mswauth.dto.ClientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
	
    ResponseEntity<?> create(ClientDto clientDto);

    ResponseEntity<?> getAllClients();

    ResponseEntity<?> getAllUserByClient(String codeClient);

    ResponseEntity<?> getAllUsers();

   ResponseEntity<?> getClient(String compteClient);

    ResponseEntity<?> update(ClientDto clientDto);

    ResponseEntity<?> getAllPaginateClient(int page, int size);
}
