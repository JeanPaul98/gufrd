package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class DestinataireMarchandiseRequest implements Serializable {

    @NotNull
    @NotBlank
    private String nom;

    @NotNull @NotBlank
    private String typeDestinataire;

    @NotNull @NotBlank
    private String telephone;

    @NotNull @NotBlank
    private String natureMarchandise;

    @NotNull @NotBlank
    private String codeSh;

    @NotNull
    private Long nombreProduit;

    @NotNull
    private Long produitGrandeConsommation;
}
