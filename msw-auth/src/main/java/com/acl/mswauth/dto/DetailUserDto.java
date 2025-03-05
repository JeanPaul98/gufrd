package com.acl.mswauth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Getter
@Setter
public class DetailUserDto {

    private String entreprise;

    private String fonction;

    private String login;

    private String tel;

    private String email;

    private String password;

    private String status;

    private String groupe;

    public DetailUserDto(String nomEntreprise, String email, String fonction,
                         String telephone, String login, String groupe) {
        this.entreprise = nomEntreprise;
        this.email = email;
        this.fonction = fonction;
        this.tel = telephone;
        this.login = login;
        this.groupe = groupe;
    }
}
