package com.acl.escalenavire.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AnnonceEscaleService {

    ResponseEntity<?>  list(int page,int size);

    ResponseEntity<?> listAnnByNumeroAan(String numeroAan);

    ResponseEntity<?> listAnnByAffect(String affreteurArrivee);

    ResponseEntity<?>  findPortByAnnonceEscale(Long id);

    ResponseEntity<?>  findNavireByAnnonceEscale(Long id);
}
