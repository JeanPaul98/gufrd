package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.produit.StructureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StructureService {

    ResponseEntity<?> listStructure();
    ResponseEntity<?> createStructure(StructureDto request);
}
