package com.acl.mswauth.dto.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/24/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class RegisterUpdateDto {

    private Long idUser;

    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private String password;



}
