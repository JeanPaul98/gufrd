package com.acl.escalenavire.services;

import com.acl.escalenavire.dto.BlDto;
import com.acl.escalenavire.models.Bl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LigneMseService {

    ResponseEntity<?> list(int page, int size);
    ResponseEntity<?> search(String numBl);
}
