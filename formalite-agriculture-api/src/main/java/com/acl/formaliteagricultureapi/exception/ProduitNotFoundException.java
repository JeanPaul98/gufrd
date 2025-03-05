package com.acl.formaliteagricultureapi.exception;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
public class ProduitNotFoundException extends RuntimeException {

    public ProduitNotFoundException(String message) {
        super(message);
    }
}
