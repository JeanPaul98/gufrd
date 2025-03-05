package com.acl.mswauth.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/15/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class UserClientDto {

    private String compteClient;

    private String email;

    private String fonction;

    private String telephoneNumber;

    private Long idGroupe;


}
