package com.acl.vbs.fret.requests;

import com.acl.vbs.fret.entities.Garant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransporteurRequest implements Serializable {
    @NotNull
    private Long garant;

    @NotNull
    @NotBlank
    private String raisonSociale;

    @NotNull
    @NotBlank
    private String telephone;

    @NotNull
    @NotBlank
    private String adresse;

    @NotNull
    @Email
    @NotBlank
    private String mail;

    @NotNull
    @NotBlank
    private String nif;

    @NotNull
    @NotBlank
    private String rccm;

    @NotNull
    @NotBlank
    private String licenceTransport;
}
