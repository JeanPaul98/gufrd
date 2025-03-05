package com.acl.mswauth.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 11/8/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class RegisterStructureDto {

    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

    private String email;

    private String mobile;

    private Long idStructure;

    private Long idGroupe;

    private String role;

}
