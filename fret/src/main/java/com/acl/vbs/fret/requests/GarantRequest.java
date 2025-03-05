package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class GarantRequest implements Serializable {
    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private String prenom;

    @NotNull
    @NotBlank
    private String telephone;

    @NotNull
    @NotBlank
    private String genre;

    @NotNull
    @NotBlank
    @Email
    private String mail;

    @NotNull
    @NotBlank
    private String cin;
}
