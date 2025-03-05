package com.acl.formaliteagricultureapi.exception;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
public class DocumentNotFoundException  extends RuntimeException{
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
