package com.acl.escalenavire.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnonceEscaleDto {

    private String portProvenance;

    private String portDestination;

    private String nomNavire;

    private String nomAffreteurDepart;

    private String nomAffreteurArrivee;

    private String imo;

    public AnnonceEscaleDto(String portProvenance,String portDestination, String nomNavire, String imo,String nomAffD,String nomAffA) {
        this.portProvenance = portProvenance;
        this.portDestination = portDestination;
        this.nomNavire = nomNavire;
        this.imo = imo;
        this.nomAffreteurDepart = nomAffD;
        this.nomAffreteurArrivee = nomAffA;
    }
}
