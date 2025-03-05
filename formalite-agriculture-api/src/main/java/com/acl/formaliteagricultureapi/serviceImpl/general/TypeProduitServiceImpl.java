package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.produit.TypeProduitDto;
import com.acl.formaliteagricultureapi.models.TypeProduit;
import com.acl.formaliteagricultureapi.models.TypePv;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.TypeProduitRepository;
import com.acl.formaliteagricultureapi.services.general.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProduitServiceImpl implements TypeProduitService {

    private final TypeProduitRepository typeProduitRepository;

    @Autowired
    private Environment env;

    public TypeProduitServiceImpl(TypeProduitRepository typeProduitRepository) {
        this.typeProduitRepository = typeProduitRepository;
    }

    @Override
    public ResponseEntity<?> creatTypeProduit(TypeProduitDto type) {
        if(type.getLibelle().isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NO_CONTENT.name(),
                    "TypeProduit n'existe pas"), HttpStatus.OK);
        }
        TypeProduit request = new TypeProduit();
        request.setLibelle(type.getLibelle());
        request.setRef(type.getRef());
        TypeProduit saveType = typeProduitRepository.save(request);

        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                env.getProperty("message.succes"), saveType), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listTypeProduit() {

        List<TypeProduit> type = typeProduitRepository.findAll();
        if(type.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    typeProduitRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), typeProduitRepository.findAll()), HttpStatus.OK);
        }
    }
}
