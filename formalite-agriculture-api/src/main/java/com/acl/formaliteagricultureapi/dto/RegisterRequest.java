package com.acl.formaliteagricultureapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	
    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String fullname;
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
    
    
}
