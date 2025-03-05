package com.acl.mswauth.servicesImpl;

import com.acl.mswauth.converter.PaysConverter;
import com.acl.mswauth.model.MswPays;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.PaysRepository;
import com.acl.mswauth.request.PaysRequest;
import com.acl.mswauth.service.PaysServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaysServicesImpl implements PaysServices {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private PaysConverter paysConverter;

    @Override
    public ResponseEntity<?> create(PaysRequest paysRequest) {
        Optional<MswPays> ms = paysRepository.findMswPaysByCodeAndAndNom(paysRequest.getCodePays(),paysRequest.getNom());
        if(ms.isPresent()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NOT_FOUND.name(),
                    "Le pays existe déja ",paysRequest),HttpStatus.NOT_FOUND);
        } else {

            MswPays mswPays = paysConverter.convertEntity(paysRequest);
            MswPays save=  paysRepository.save(mswPays);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                    "Opération réussi ",save),HttpStatus.OK);

        }

    }

    @Override
    public ResponseEntity<?> getAllPays() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.CREATED.name(),
                "Opération réussie ",paysRepository.findAll()),HttpStatus.OK);
    }

}
