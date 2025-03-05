package com.acl.escalenavire.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BlService {
    ResponseEntity<?> list(int page, int size);

    ResponseEntity<?> searchNumBl(String numAtp);
}
