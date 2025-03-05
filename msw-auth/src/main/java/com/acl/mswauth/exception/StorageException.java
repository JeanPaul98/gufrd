package com.acl.mswauth.exception;

/**
 * @author Zansouyé on 29/08/2024
 * @project formalite-agriculture-api
 */
public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
