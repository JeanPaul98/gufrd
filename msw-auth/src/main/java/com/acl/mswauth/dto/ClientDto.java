package com.acl.mswauth.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientDto implements Serializable {

    private Long id;
	
	private String nomClient;
    
    private String codePays;
    
    private String codeClient;

    private String clientAdresse;

    private String nif;

    private String structure;

    public ClientDto(String nomClient, String compteClient, String clientAdresse, String nif, String codePays) {
        this.nomClient = nomClient;
        this.codePays = codePays;
        this.clientAdresse = clientAdresse;
        this.nif = nif;
        this.codeClient = compteClient;

    }
}
