package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class HttpResponse implements Serializable {
    private String message;
    private int status;
}
