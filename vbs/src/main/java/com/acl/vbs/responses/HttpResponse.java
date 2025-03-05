package com.acl.vbs.responses;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Data
public abstract class HttpResponse implements Serializable {
    private String message;
    private int status;
}
