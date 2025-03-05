package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApprentiRequest implements Serializable {
    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private String prenoms;

    @NotNull
    @NotBlank
    private String nationalite;

    @NotNull
    @NotBlank
    private String dateNaissance;

    @NotNull
    @NotBlank
    private String genre;

    @NotNull
    @NotBlank
    private String telephone;

    @NotNull
    @NotBlank
    private String adresse;

    @NotNull
    private long conducteur;

}
