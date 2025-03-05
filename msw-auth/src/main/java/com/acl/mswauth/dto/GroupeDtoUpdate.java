package com.acl.mswauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 01/10/2024
 * @project msw-auth
 */
@Getter
@Setter
public class GroupeDtoUpdate {

    @NotBlank
    private Long id;

    @NotBlank
    private String nomGroupe;
}
