package com.acl.mswauth.servicesImpl.application;

import com.acl.mswauth.dto.user.UserApplisGroupeDto;
import com.acl.mswauth.model.MswGroupe;
import com.acl.mswauth.model.MswUserApplication;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.GroupeRepository;
import com.acl.mswauth.repositories.UserApplicationRepository;
import com.acl.mswauth.service.application.MswApplicationGroupeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author kol on 03/10/2024
 * @project msw-auth
 */
@Service
public class MswAppliGroupeServiceImpl implements MswApplicationGroupeService {

   private final UserApplicationRepository userApplicationRepository;

   private final GroupeRepository groupeRepository;

   @Autowired
   private Environment env;

    public MswAppliGroupeServiceImpl(UserApplicationRepository userApplicationRepository, GroupeRepository groupeRepository) {
        this.userApplicationRepository = userApplicationRepository;
        this.groupeRepository = groupeRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<?> removeApplicationGroupe(UserApplisGroupeDto request) {
        Optional<MswUserApplication> userApp = userApplicationRepository.findByInfosGroupe(request.getIdApplication(),
                request.getIdGroupe(), request.getCodePort());
        if (userApp.isPresent()) {
           userApplicationRepository.delete(userApp.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"), request), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NO_CONTENT.name(),
                    env.getProperty("message.application.echec")), HttpStatus.OK);

        }

    }

    @Override
    public ResponseEntity<?> getAllApplicationByGroupe(Long groupId) {
        Optional<MswGroupe> mswGroupeOptional = groupeRepository.findById(groupId);
        if (mswGroupeOptional.isPresent()) {
            List<MswUserApplication> mswUserApplications = userApplicationRepository.findByGroupe(mswGroupeOptional.get());
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"), mswUserApplications), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.vide")), HttpStatus.OK);

        }
    }
}
