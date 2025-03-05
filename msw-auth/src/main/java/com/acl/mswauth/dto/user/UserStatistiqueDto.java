package com.acl.mswauth.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/21/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class UserStatistiqueDto {

    private long nombreInscrit;

    private long nombreEntreprise;

    private long groupeInscrits;

    public UserStatistiqueDto(long nombreUser, long nombreEntreprises) {
        this.nombreInscrit = nombreUser;
        this.nombreEntreprise = nombreEntreprises;
    }
}
