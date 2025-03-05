package com.acl.formaliteagricultureapi.dto.formalite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author kol on 10/21/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class FormaliteStatistiqueDto {

    private String entreprise;
    private String structure;
    private String navire;
    private String affreteur;
    private String nomDemandeur;
    private String typeFormalite;
    private Date dateDeclaration;
    private Date dateTraitement;
    private long duree;


    public FormaliteStatistiqueDto(String entreprise, String nomStructure, String navire,
                                   String affreteur, Date dateDeclaration, String nomDemandeur,
                                   Date dateTraitement, String typeFormalite) {
        this.entreprise = entreprise;
        this.structure = nomStructure;
        this.navire = navire;
        this.affreteur = affreteur;
        this.nomDemandeur = nomDemandeur;
        this.dateDeclaration = dateDeclaration;
        this.dateTraitement = dateTraitement;
        this.typeFormalite = typeFormalite;
    }


}
