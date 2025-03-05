package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import com.acl.formaliteagricultureapi.models.enumeration.StatutAgrement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author kol on 11/18/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class AutorisationAgrementDto {

    private String numero;

    private String code;

    private String activite;

    private String observation;

    private Etat etat;

    private StatutAgrement statutAgrement;

    private Date dateAgrement;


    public AutorisationAgrementDto(String numero, String activite, Date dateAgrement) {
        this.numero = numero;
        this.activite = activite;
        this.dateAgrement = dateAgrement;
    }
}
