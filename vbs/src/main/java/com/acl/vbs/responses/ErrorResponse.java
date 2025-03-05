package com.acl.vbs.responses;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends HttpResponse  {
    private String reason;
    private Object validations;
}
