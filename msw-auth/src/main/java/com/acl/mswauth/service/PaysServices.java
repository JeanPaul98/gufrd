package com.acl.mswauth.service;

import com.acl.mswauth.request.PaysRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaysServices {

    ResponseEntity<?> create(PaysRequest paysRequest);
    ResponseEntity<?> getAllPays();

}
