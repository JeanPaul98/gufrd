package com.acl.mswauth.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 10/6/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date date, String message, String description) {
        this.timestamp = date;
        this.message = message;
        this.details = description;
    }
}
