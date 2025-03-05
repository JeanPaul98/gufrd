package com.acl.vbs.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BonEntreeCamionRequest implements Serializable {
    @NotNull
    @NotBlank
    private String immatriculation;
    @NotNull
    @NotBlank
    private String codeSensTrafic;
    @NotNull
    private String remorque;
    @NotNull
    private Long idSitePesage;
    @NotNull
    private Boolean transporteMse;

    @NotNull
    @NotBlank
    private String chargeur;
    @NotNull
    @NotBlank
    private String bonChargement;
    @NotNull @Min(0)
    private BigDecimal poidsChargeEnleve;

    private List<Long> idLigneMseList;
}