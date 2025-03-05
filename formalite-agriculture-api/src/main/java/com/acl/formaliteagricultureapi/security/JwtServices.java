package com.acl.formaliteagricultureapi.security;


import com.acl.formaliteagricultureapi.models.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtServices {

    String extractUserName(String token);

    String getUserIdFromJWT(String token);

    String generateToken(User user);

    boolean isValidToken(String token);
}
