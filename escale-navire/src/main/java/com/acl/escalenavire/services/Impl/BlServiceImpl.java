package com.acl.escalenavire.services.Impl;

import com.acl.escalenavire.dto.AnnonceEscaleInt;
import com.acl.escalenavire.dto.BlInt;
import com.acl.escalenavire.dto.LigneMseInt;
import com.acl.escalenavire.models.Bl;
import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.repositories.BlRepository;
import com.acl.escalenavire.services.BlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlServiceImpl implements BlService {

    private final BlRepository blRepository;

    @Autowired
    private Environment env;

    public BlServiceImpl(BlRepository blRepository) {
        this.blRepository = blRepository;
    }



    @Override
    public ResponseEntity<?> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bl> bls = blRepository.list(pageable);
        if(bls.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "list vide",bls), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Operation effectuer avec succes",bls), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> searchNumBl(String numAtp) {
        List<AnnonceEscaleInt> ligne = blRepository.findNumBlByAtp(numAtp);
        if(ligne.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "list vide",ligne), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Operation effectuer avec succes",ligne), HttpStatus.OK);
        }
    }

}
