package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CamionRequest implements Serializable {
    @NotNull
    private Long transporteur;

    @NotNull
    @NotBlank
    private String immatriculation;

    @NotNull
    @Min(0)
    private Long chargeUtile;

    @NotNull
    @Min(0)
    private Long capaciteAutorisee14;

    @NotNull
    private Date dateExpVisiteTechnique;

    @NotNull
    private Date dateExpAssurance;

    @NotNull
    @NotBlank
    private String nationalite;

    @NotNull
    @NotBlank
    private String silhouette;

    @NotNull
    @NotBlank
    private String marque;

    @NotNull
    @NotBlank
    private String modeleCarteGrise;

    @NotNull
    @NotBlank
    private String numeroCarteGrise;

    @NotNull
    @NotBlank
    private String numeroCarteTransport;
}