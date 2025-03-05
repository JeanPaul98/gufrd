package com.acl.mswauth.service.application;

import com.acl.mswauth.dto.ApplicationDto;
import com.acl.mswauth.request.UserApplicationRequest;
import com.acl.mswauth.security.CurrentUser;
import com.acl.mswauth.security.impl.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MswApplicationService {

    ResponseEntity<?> create(ApplicationDto applicationDto);

    ResponseEntity<?> getAllApplications();

    ResponseEntity<?> addApplicationGroupePort(UserApplicationRequest request);

    ResponseEntity<?> getAllApplicationsByGroupeId(Long groupeId);

    ResponseEntity<?> getAllApplicationsByGroupeName(String groupeName);

    ResponseEntity<?> getAllAplicationByClient(@CurrentUser UserPrincipal userPrincipal);

    ResponseEntity<?> getAllAplicationByKeycloakUser(String user);


    ResponseEntity<?> getApplicationDetails(long applid);

    ResponseEntity<?> update(ApplicationDto request);
}
