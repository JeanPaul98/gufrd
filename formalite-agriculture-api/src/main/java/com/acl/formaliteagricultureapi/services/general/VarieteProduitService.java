package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.produit.VarieteProduitDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface VarieteProduitService {
    ResponseEntity<?> listVarieteProduit();
    ResponseEntity<?> createVarieteProduit(VarieteProduitDto request);
}
