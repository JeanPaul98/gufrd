package com.acl.vbs.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseModel {

    private String status;
    private String message;
    private Object result;

    public ApiResponseModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponseModel(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
