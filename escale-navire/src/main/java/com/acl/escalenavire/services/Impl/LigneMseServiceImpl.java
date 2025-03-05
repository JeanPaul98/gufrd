package com.acl.escalenavire.services.Impl;

import com.acl.escalenavire.dto.BlDto;
import com.acl.escalenavire.dto.LigneMseDto;
import com.acl.escalenavire.dto.LigneMseInt;
import com.acl.escalenavire.models.Bl;
import com.acl.escalenavire.models.LigneMse;
import com.acl.escalenavire.playload.ApiResponseModel;
import com.acl.escalenavire.repositories.LigneMseRepository;
import com.acl.escalenavire.services.LigneMseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LigneMseServiceImpl implements LigneMseService {

    private final LigneMseRepository ligneMseRepository;

    @Autowired
    private Environment env;

    public LigneMseServiceImpl(LigneMseRepository ligneMseRepository) {
        this.ligneMseRepository = ligneMseRepository;
    }

    @Override
    public ResponseEntity<?> list( int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<LigneMseInt> list = ligneMseRepository.list(pageable);
        if(list.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "list vide",list), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Operation effectuer avec succes", list), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> search(String numBl) {
        List<LigneMseInt> ligne = ligneMseRepository.findLigneMseNumBl(numBl);
        if(ligne.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "list vide",ligne), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    "Operation effectuer avec succes",ligne), HttpStatus.OK);
        }
    }

//    @Override
//    public ResponseEntity<?> search(String numBl) {
//        List<LigneMse> lignes = ligneMseRepository.findLigneMseNumBl(numBl);
//        if(lignes.isEmpty()){
//            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
//                    "list vide",lignes), HttpStatus.OK);
//        }else{
//            List<LigneMseDto> dtoList = lignes.stream()
//                    .map(ligne -> {
//                        LigneMseDto dto = new LigneMseDto();
//                        dto.setPlomb(ligne.getPlomb());
//                        dto.setNumConteneur(ligne.getNumConteneur().getNumConteneur());
//                        dto.setDescMse(ligne.getDescMse());
//                        dto.setLibMarchandise(ligne.getCodeMarchandise().getLibMarchandise());
//                        dto.setPoidsBrut(ligne.getPoidsBrut());
//                        return dto;
//                    })
//                    .collect(Collectors.toList());
//            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
//                    "Operation effectuer avec succes",dtoList), HttpStatus.OK);
//        }
//    }

}
