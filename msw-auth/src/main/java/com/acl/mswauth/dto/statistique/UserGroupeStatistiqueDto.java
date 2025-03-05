package com.acl.mswauth.dto.statistique;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/23/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class UserGroupeStatistiqueDto {

    private String nomGroupe;

    private int nombre;

    public UserGroupeStatistiqueDto(String nomGroupe, int nombre) {
        this.nomGroupe = nomGroupe;
        this.nombre = nombre;
    }
}
