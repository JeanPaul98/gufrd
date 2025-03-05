package com.acl.mswauth.dto.user;

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
public class DetailUserDto {

    private Long idUser;
    private String login;
    private String fullname;
    private String lastName;
    private String firstName;
    private String email;
    private String mobile;
    private String entreprise;
    private String fonction;
    private String groupe;
    private String compteClient;

    public DetailUserDto(Long idUser, String username, String email, String nomEntreprise,
                         String telephone, String fullName, String lastName, String firstName, String fonction, String groupe,String compteClient) {
        this.idUser = idUser;
        this.login = username;
        this.fullname = fullName;
        this.email = email;
        this.mobile = telephone;
        this.lastName = lastName;
        this.firstName = firstName;
        this.entreprise = nomEntreprise;
        this.fonction = fonction;
        this.groupe = groupe;
        this.compteClient = compteClient;
    }
}
