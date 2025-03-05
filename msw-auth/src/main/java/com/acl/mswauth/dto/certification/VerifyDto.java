package com.acl.mswauth.dto.certification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 11/7/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class VerifyDto {

    private String code;
    private String nif;
}
