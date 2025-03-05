package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChargeurRequest implements Serializable {
    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private String nationalite;

    @NotNull
    @NotBlank
    private String telephone;

    @NotNull
    @NotBlank
    @Email
    private String mail;

    @NotNull
    private Boolean repartitionFret;

    @NotNull
    @NotBlank
    private String compteClient;
}
