package com.acl.formaliteagricultureapi.services.general;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TypePvService {

    public ResponseEntity<?> getAllTypePv();
}
