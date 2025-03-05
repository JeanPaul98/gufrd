package com.acl.mswauth.service;

import com.acl.mswauth.dto.MswConnectedUserDto;
import com.acl.mswauth.model.MswConnectedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ConnectedUserService {

    MswConnectedUser save(MswConnectedUserDto user);

    Optional<MswConnectedUser> findByLogin(String login);

    void deleteByLogin(String login);

    ResponseEntity<?> findBySession(String session);
}
