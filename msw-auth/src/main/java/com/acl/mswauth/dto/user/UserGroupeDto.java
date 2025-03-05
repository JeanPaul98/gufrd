package com.acl.mswauth.dto.user;

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
public class UserGroupeDto {

    private int ligneMaritime;
    private int transitaire;
    private int consignataire;
    private int chargeur;
    private int operateurTerminaux;

}
