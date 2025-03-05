package com.acl.mswauth.exception;

/**
 * @author Zansouyé on 29/08/2024
 * @project formalite-agriculture-api
 */
public class StorageFileNotFoundException extends StorageException{

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
