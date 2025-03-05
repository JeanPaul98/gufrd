package com.acl.mswauth.service;

import com.acl.mswauth.repositories.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    ResponseEntity<?> getAllRole();

}
