package com.acl.mswauth.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 03/10/2024
 * @project msw-auth
 */
@Getter
@Setter
public class UserApplisGroupeDto {

    @NotBlank
    private String codePort;

    private Long idGroupe;

    private Long  idApplication;
}
