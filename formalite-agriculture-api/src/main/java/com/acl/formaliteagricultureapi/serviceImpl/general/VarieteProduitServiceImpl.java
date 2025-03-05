package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.dto.produit.VarieteProduitDto;
import com.acl.formaliteagricultureapi.models.VarieteProduit;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.VarieteProduitRepository;
import com.acl.formaliteagricultureapi.services.general.VarieteProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarieteProduitServiceImpl implements VarieteProduitService {

    private final VarieteProduitRepository varieteProduitRepository;

    @Autowired
    private Environment env;

    public VarieteProduitServiceImpl(VarieteProduitRepository varieteProduitRepository) {
        this.varieteProduitRepository = varieteProduitRepository;
    }

    @Override
    public ResponseEntity<?> listVarieteProduit() {
        List<VarieteProduit> var = varieteProduitRepository.findAll();
        if(var.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    varieteProduitRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), varieteProduitRepository.findAll()), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> createVarieteProduit(VarieteProduitDto request) {

        VarieteProduit var = new VarieteProduit();
        var.setLibelle(request.getLibelle());
        var.setRef(request.getRef());

        VarieteProduit saved = varieteProduitRepository.save(var);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), saved), HttpStatus.OK);
        }
}
