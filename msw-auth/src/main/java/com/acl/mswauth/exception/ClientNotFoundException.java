package com.acl.mswauth.exception;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String message) {
        super(message);
    }
}
