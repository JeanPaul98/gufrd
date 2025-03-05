package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import com.acl.formaliteagricultureapi.models.Societe;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
public interface SocieteService {
    ResponseEntity<?> listSociete();
    ResponseEntity<?> addSociete(SocieteDto societeDto) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;
    ResponseEntity<?> updateSociete(SocieteDto societeDto,long id);
}