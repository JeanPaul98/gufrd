package com.acl.mswauth.service.user;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acl.mswauth.request.RegisterRequest;

@Service
public interface UserService {
	
     ResponseEntity<?> storeUser(RegisterRequest request);
    
     ResponseEntity<?> storeStructureUser(RegisterRequest request);
     
     ResponseEntity<?>  findByCodeClient(String codeClient);
     
     ResponseEntity<?>  findByStructure(Long idStructure);
     
     ResponseEntity<?>  findByStatus(boolean status);
     
     ResponseEntity<?>  findAll();

     ResponseEntity<?>  deleteUser(long id);

     ResponseEntity<?>  actifUser(long id);

     ResponseEntity<?>  findValideUser(boolean etat);


     ResponseEntity<?> findCurrentUser(String currentUserName);

     ResponseEntity<?> deleteConnectedUser(String currentUserName);

     ResponseEntity<?>  findByStatusPageable(boolean status, int page, int size);

     ResponseEntity<?> findCurrentUserDetail(String currentUserName);
}
