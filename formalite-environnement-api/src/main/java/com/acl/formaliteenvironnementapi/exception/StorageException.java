package com.acl.formaliteenvironnementapi.exception;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
