package com.acl.mswauth.servicesImpl;

import com.acl.mswauth.converter.MswFormaliteAppConverter;
import com.acl.mswauth.dto.MswFormaliteAppliDto;
import com.acl.mswauth.model.MswApplication;
import com.acl.mswauth.model.MswFormaliteApplication;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.ApplicationRepository;
import com.acl.mswauth.repositories.MswFormaliteAppliRepository;
import com.acl.mswauth.service.MswFormaliteAppliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MswFormaliteAppliServiceImp implements MswFormaliteAppliService {

    private final MswFormaliteAppliRepository mswFormaliteAppliRepository;
    private final ApplicationRepository applicationRepository;
    private final MswFormaliteAppConverter mswFormaliteAppConverter;

    @Autowired
    private Environment env;

    public MswFormaliteAppliServiceImp(MswFormaliteAppliRepository mswFormaliteAppliRepository, ApplicationRepository applicationRepository, MswFormaliteAppConverter mswFormaliteAppConverter) {
        this.mswFormaliteAppliRepository = mswFormaliteAppliRepository;
        this.applicationRepository = applicationRepository;
        this.mswFormaliteAppConverter = mswFormaliteAppConverter;
    }


    @Override
    public ResponseEntity<?> save(MswFormaliteAppliDto mswFormaliteAppliDto) {

        Optional<MswApplication> mswApplication = applicationRepository.findByReference(mswFormaliteAppliDto.getReferenceApplication());

        if (mswApplication.isPresent()) {
            MswFormaliteApplication mswFormaliteApplication = mswFormaliteAppConverter.convertEntity(mswFormaliteAppliDto);
            mswFormaliteApplication.setMswApplication(mswApplication.get());
            mswFormaliteApplication.setCreatedAt(new Date());
            mswFormaliteAppliRepository.save(mswFormaliteApplication);
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.application.sucess"),
                    mswFormaliteAppliDto), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.notfound")),HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<?> findAllApplications() {

        List<MswFormaliteApplication> formaliteApplications = mswFormaliteAppliRepository.findAll();
        if(!formaliteApplications.isEmpty()) {
            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.sucess"),
                    formaliteApplications),HttpStatus.OK);
        } else  {
            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(), env.getProperty("message.application.vide"),
                    formaliteApplications),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> update(MswFormaliteAppliDto request) {
        Optional<MswFormaliteApplication> applis = mswFormaliteAppliRepository.
                findByApplication(request.getId());
        if(applis.isPresent()) {

            Optional<MswApplication> mswApplication = applicationRepository.findByReference(request.getReferenceApplication());
            if(mswApplication.isPresent()) {
                applis.get().setName(request.getName());
                applis.get().setDescription(request.getDescription());
                applis.get().setMswApplication(mswApplication.get());
                applis.get().setUrl(request.getUrl());
                applis.get().setUpdatedAt(new Date());
                return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.sucess"),
                     mswFormaliteAppliRepository.save(applis.get())),HttpStatus.OK);
            } else {
                return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                        env.getProperty("message.application.notfound")),HttpStatus.OK);
            }

        } else {
            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.notfound")),HttpStatus.OK);
        }

    }
}
