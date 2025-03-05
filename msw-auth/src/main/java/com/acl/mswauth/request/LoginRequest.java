package com.acl.mswauth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
