package com.acl.formaliteagricultureapi.serviceImpl.general;

import com.acl.formaliteagricultureapi.models.Inspecteur;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.InspecteurRepository;
import com.acl.formaliteagricultureapi.services.general.InspecteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspecteurServiceImpl implements InspecteurService {

    private final InspecteurRepository inspecteurRepository;

    @Autowired
    private Environment env;

    public InspecteurServiceImpl(InspecteurRepository inspecteurRepository) {
        this.inspecteurRepository = inspecteurRepository;
    }

    @Override
    public ResponseEntity<?> listInspecteurs() {
        List<Inspecteur>inspecteurs=inspecteurRepository.findAll();

        if(inspecteurs.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.list.vide"),
                    inspecteurRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), inspecteurRepository.findAll()), HttpStatus.OK);
        }

    }
}
