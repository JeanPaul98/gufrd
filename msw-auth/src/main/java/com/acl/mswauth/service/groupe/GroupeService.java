package com.acl.mswauth.service.groupe;

import com.acl.mswauth.dto.ClientGroupeDto;
import com.acl.mswauth.dto.GroupeDtoUpdate;
import com.acl.mswauth.request.GroupeClientRequest;
import com.acl.mswauth.dto.GroupeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GroupeService {

    ResponseEntity<?> create(GroupeDto groupeDto);

    ResponseEntity<?> update(GroupeDtoUpdate groupeDto);


    ResponseEntity<?> addClientGroupe(GroupeClientRequest groupeClientRequest);
    
    ResponseEntity<?> getAllGroupesByClientId(Long id);
    
    ResponseEntity<?> getAllGroupesByClientCompte(String compteClient);

    ResponseEntity<?>  addGroupesClient(ClientGroupeDto request);
}
