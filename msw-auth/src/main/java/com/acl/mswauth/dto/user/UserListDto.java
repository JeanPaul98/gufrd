package com.acl.mswauth.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/22/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class UserListDto {

    private String entreprise;
    private String fullname;
    private String login;
    private String telephone;
    private String email;
    private  String fonction;
    private  String groupe;

    public UserListDto(String nomEntreprise, String fullName, String login, String telephone,
                       String email, String fonction, String groupe) {
        this.entreprise = nomEntreprise;
        this.fullname = fullName;
        this.login = login;
        this.telephone = telephone;
        this.email = email;
        this.fonction = fonction;
        this.groupe = groupe;
    }
}
