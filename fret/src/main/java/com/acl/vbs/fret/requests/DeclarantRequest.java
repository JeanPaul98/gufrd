package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeclarantRequest implements Serializable {

    @NotNull
    @NotBlank
    private String maisonTransit;

    @NotNull
    @NotBlank
    private String telephone;

    @NotNull
    @NotBlank
    private String mail;

    @NotNull
    @NotBlank
    private String compteClient;
}
