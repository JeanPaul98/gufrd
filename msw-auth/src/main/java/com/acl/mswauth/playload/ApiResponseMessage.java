package com.acl.mswauth.playload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Getter
@Setter
public class ApiResponseMessage {

    private String status;

    private String message;

    public ApiResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
