package com.acl.mswauth.service;


import com.acl.mswauth.request.LoginRequest;
import com.acl.mswauth.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {
	
    ResponseEntity<?> authenticate(LoginRequest loginRequest);

    ResponseEntity<?> authenticateUser(Authentication authentication);

    ResponseEntity<?> currentUser(String email);

    String currentUserEmail();
}
