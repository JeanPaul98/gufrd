package com.acl.formaliteagricultureapi.services.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/11/24
 * @project formalite-agriculture-api
 */
@Service
public interface AuthService {

    public ResponseEntity<?> curentUser();

    public String connectedUser();

}
