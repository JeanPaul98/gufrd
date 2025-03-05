package com.acl.mswauth.dto.formalite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FormaliteStatReportDto {

    private String entreprise;
    private String structure;
    private String navire;
    private String affreteur;
    private String societe;
    private String nomDemandeur;
    private String typeFormalite;
    private String dateDeclaration;
    private String dateTraitement;
    private String delaiPrevu;
    private String observation;
    private String duree;



}
