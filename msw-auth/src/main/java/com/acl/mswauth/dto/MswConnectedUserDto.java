package com.acl.mswauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MswConnectedUserDto {

    private String userLogin;
    private String compteClient;
    private String portCode;
    private String countryCode;
    private String fullName;
    private String entreprise;


    public MswConnectedUserDto(String compteClient, String login,
                               String fullname, String codePortClient,
                               String code, String entreprise) {
        this.compteClient = compteClient;
        this.fullName = fullname;
        this.userLogin = login;
        this.portCode = codePortClient;
        this.countryCode = code;
        this.entreprise = entreprise;
    }
}
