package com.acl.mswauth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acl.mswauth.request.StructureRequest;

@Service
public interface StructureService {
	
    ResponseEntity<?> create(StructureRequest request);
	
	ResponseEntity<?> update(Long structureId, StructureRequest request);
	
	ResponseEntity<?> getAllByStructureParente(Long structureParenteId);	
	
	ResponseEntity<?> getAllByPays(String codePays);
	
	ResponseEntity<?> assignStructureParente(Long strutureId, Long StructureParenteId);
	
	
	
	

}
