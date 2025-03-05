package com.acl.mswauth.service.application;

import com.acl.mswauth.dto.user.UserApplisGroupeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MswApplicationGroupeService {

    ResponseEntity<?> removeApplicationGroupe(UserApplisGroupeDto request);

    ResponseEntity<?> getAllApplicationByGroupe(Long groupId);
}
