package com.acl.formaliteagricultureapi.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String login;
    private String password;
    private String fullname;
    private String email;
    private String fonction;
    private String mobile;
}
