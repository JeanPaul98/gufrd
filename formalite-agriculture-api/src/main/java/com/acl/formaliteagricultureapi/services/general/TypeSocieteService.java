package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.societe.TypeSocieteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TypeSocieteService {
    ResponseEntity<?> listTypeSociete();
    ResponseEntity<?> addTypeSociete(TypeSocieteDto typeSocieteDto);
    ResponseEntity<?> updateTypeSociete(TypeSocieteDto typeSocieteDto,long id);
}
