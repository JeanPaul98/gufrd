package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.models.Produit;
import com.acl.formaliteagricultureapi.models.TypePv;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.TypePvRepository;
import com.acl.formaliteagricultureapi.services.general.TypePvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePvServiceImp implements TypePvService {

    private final TypePvRepository typePvRepository;

    @Autowired
    private Environment env;

    public TypePvServiceImp(TypePvRepository typePvRepository) {
        this.typePvRepository = typePvRepository;
    }

    @Override
    public ResponseEntity<?> getAllTypePv() {

        List<TypePv> typePv = typePvRepository.findAll();
        if(typePv.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    typePvRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), typePvRepository.findAll()), HttpStatus.OK);
        }
    }
}
