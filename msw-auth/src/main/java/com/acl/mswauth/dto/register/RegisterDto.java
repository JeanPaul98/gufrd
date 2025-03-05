package com.acl.mswauth.dto.register;

import com.acl.mswauth.dto.user.UserClientDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RegisterDto {
	
    @NotNull
    @NotBlank
    private String login;
    @NotNull
    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

    @NotNull
    private List<UserClientDto> clients;

    
    
}
