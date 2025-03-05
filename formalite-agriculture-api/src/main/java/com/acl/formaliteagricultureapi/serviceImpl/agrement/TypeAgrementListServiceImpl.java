package com.acl.formaliteagricultureapi.serviceImpl.agrement;

import com.acl.formaliteagricultureapi.models.Agrement;
import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.repository.AgrementRepository;
import com.acl.formaliteagricultureapi.services.agrement.TypeAgrementListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Service
public class TypeAgrementListServiceImpl implements TypeAgrementListService {

    private Logger logger= LoggerFactory.getLogger(TypeAgrementListServiceImpl.class);

    private final AgrementRepository agrementRepository;

    public TypeAgrementListServiceImpl(AgrementRepository agrementRepository) {
        this.agrementRepository = agrementRepository;
    }

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<?> listTypeAgrement() {
        List<Agrement> agrementServiceList = agrementRepository.findAll();
        if(agrementServiceList.isEmpty()){
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    env.getProperty("message.list.vide"), agrementRepository.findAll()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.succes"), agrementServiceList), HttpStatus.OK);
        }

    }

}
