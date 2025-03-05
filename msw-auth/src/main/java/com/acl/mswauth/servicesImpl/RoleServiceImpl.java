package com.acl.mswauth.servicesImpl;

import com.acl.mswauth.playload.ApiResponseModel;
import com.acl.mswauth.repositories.RoleRepository;
import com.acl.mswauth.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<?> getAllRole() {
        return new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),
                "Opératiion effectué avec succès",roleRepository.findAll()),HttpStatus.OK
        );
    }

}
