package com.acl.formaliteagricultureapi.serviceImpl.auth;


import com.acl.formaliteagricultureapi.playload.ApiResponseModel;
import com.acl.formaliteagricultureapi.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author kol on 10/11/24
 * @project formalite-agriculture-api
 */
@Service
public class AuthServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private Environment env;

    @Override
    public ResponseEntity<?> curentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("utilisateur {} ", currentUserName);

        return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.succes"),
                currentUserName), HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(new ApiResponseModel(HttpStatus.OK.name(),env.getProperty("message.echec")),
                    HttpStatus.OK);
        }
    }

    @Override
    public String connectedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            logger.info("utilisateur {} ", currentUserName);
            return currentUserName;
        } else  {
            return null;
        }
    }
}
