package com.acl.mswauth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequest {
	
    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String password;

    private String fullname;
    private String firstName;
    private String lastName;

    @NotNull
    @NotBlank
    private String email;    
    @NotNull
    @NotBlank
    private String mobile;    
    @NotNull
    @NotBlank
    private String fonction;
    
    private String codeClient;
    
    private Long structureId;

    private String role;
    
    
}
