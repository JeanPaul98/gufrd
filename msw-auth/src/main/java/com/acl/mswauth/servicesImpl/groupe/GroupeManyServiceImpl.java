package com.acl.mswauth.servicesImpl.groupe;

import com.acl.mswauth.converter.GroupeConverter;
import com.acl.mswauth.model.MswGroupe;
import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.GroupeRepository;
import com.acl.mswauth.service.groupe.GroupeManyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Service
public class GroupeManyServiceImpl implements GroupeManyService {

 private final  GroupeRepository groupeRepository;

    @Autowired
    GroupeConverter groupeConverter;
    @Autowired
    Environment env ;

    public GroupeManyServiceImpl(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    @Override
    public ResponseEntity<?> getAllGroupes() {
        List<MswGroupe> groupes = groupeRepository.findAll();
        if(!groupes.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                    env.getProperty("message.application.sucess"),groupes),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponseModel(HttpStatus.NO_CONTENT.name(),
                    env.getProperty("message.application.vide"),groupes),HttpStatus.OK);

        }
    }
}
