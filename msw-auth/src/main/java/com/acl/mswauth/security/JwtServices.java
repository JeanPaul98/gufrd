package com.acl.mswauth.security;


import com.acl.mswauth.model.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtServices {

    String extractUserName(String token);

    String getUserIdFromJWT(String token);

    String generateToken(User user);

    boolean isValidToken(String token);
}
