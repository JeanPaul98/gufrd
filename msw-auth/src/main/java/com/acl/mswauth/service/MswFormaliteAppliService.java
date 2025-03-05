package com.acl.mswauth.service;

import com.acl.mswauth.dto.MswFormaliteAppliDto;
import com.acl.mswauth.model.MswFormaliteApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MswFormaliteAppliService {

    ResponseEntity<?> save(MswFormaliteAppliDto request);

    ResponseEntity<?> findAllApplications();

    ResponseEntity<?> update(MswFormaliteAppliDto request);
}
