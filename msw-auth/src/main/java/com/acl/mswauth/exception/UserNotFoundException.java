package com.acl.mswauth.exception;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
