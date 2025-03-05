package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class MSWHttpClientResponse implements Serializable {
    private String message;
    private String status;
    private Object result;
}
