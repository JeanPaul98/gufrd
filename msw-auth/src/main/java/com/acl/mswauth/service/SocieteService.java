package com.acl.mswauth.service;

import com.acl.mswauth.dto.societe.SocieteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SocieteService {
    ResponseEntity<?> listSociete();
    ResponseEntity<?> addSociete(SocieteDto societeDto);
    ResponseEntity<?> updateSociete(SocieteDto societeDto,long id);
}