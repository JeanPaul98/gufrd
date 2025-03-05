package com.acl.mswauth.dto.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String login;
    private String password;
    private String fullname;
    private String lastName;
    private String firstName;
    private String email;
    private String fonction;
    private String mobile;
}
