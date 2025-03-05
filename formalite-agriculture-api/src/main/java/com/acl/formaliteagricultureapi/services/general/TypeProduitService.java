package com.acl.formaliteagricultureapi.services.general;

import com.acl.formaliteagricultureapi.dto.produit.TypeProduitDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TypeProduitService {

    ResponseEntity<?> creatTypeProduit(TypeProduitDto type);
    ResponseEntity<?> listTypeProduit();
}
