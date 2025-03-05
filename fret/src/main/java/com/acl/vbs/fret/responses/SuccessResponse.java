package com.acl.vbs.fret.responses;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse extends HttpResponse {
    private Object data;
}
