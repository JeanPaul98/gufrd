package com.acl.vbs.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DeclarationResponse {
    private String immatriculation;
    private String nomConducteur;
    private String remorque;
    private String nomTransitaire;
    private String raisonSocialTransitaire;
    private BigDecimal poidsVide;
    private BigDecimal poidsCharge;
    private String observationPesage;
    private String numBonEntreeCamion;
    private String libellePays;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateBonEntree;
    private BigDecimal redevance;
}
