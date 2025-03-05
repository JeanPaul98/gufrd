package com.acl.vbs.exceptions;

public class CamionNotFoundException extends RuntimeException {
    public CamionNotFoundException(String message) {
        super(message);
    }
}
