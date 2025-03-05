package com.acl.formaliteagricultureapi.dto.agrement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class DetAgrementDto {

    private String numeroAgrement;

    private String nomSociete;

    private String activite;

    private String adresse;

    public DetAgrementDto(String numero, String activite, String raisonSociale, String adresse) {
        this.numeroAgrement = numero;
        this.nomSociete = raisonSociale;
        this.activite = activite;
        this.adresse = adresse;
    }
}
