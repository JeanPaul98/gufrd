package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.produit.InsertProduitDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProduitService {

    ResponseEntity<?> listProduit();
    ResponseEntity<?> create(InsertProduitDto request);
    ResponseEntity<?> listProduitByRef(String ref);
}
