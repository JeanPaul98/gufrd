package com.acl.mswauth.dto.certification;

import com.acl.mswauth.model.enumeration.TypeDemande;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
@Getter
@Setter
@ToString
public class DmdCertificationDto {

    private String  nif;
    private String raisonSocial;
    private String email;
    private TypeDemande typeDemande;



}
