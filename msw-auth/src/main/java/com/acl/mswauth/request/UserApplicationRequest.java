package com.acl.mswauth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationRequest implements Serializable {

    @NotBlank
    private String codePort;
    @NotBlank
    private String groupe;
    @NotBlank
    private String application;

    private String url;
}
