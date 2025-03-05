package com.acl.formalitesanteapi.exception;

/**
 * @author jean paul 24/09/2024
 * @project formalite-sante-api
 */
public class StorageFileNotFoundException extends StorageException{

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
