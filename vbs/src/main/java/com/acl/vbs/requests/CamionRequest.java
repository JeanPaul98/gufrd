package com.acl.vbs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CamionRequest implements Serializable {
    @NotNull @NotBlank
    private String immatriculation;
    @NotBlank @NotNull
    private String nomConducteur;
    @NotBlank @NotNull
    private String codePays;
    @NotBlank @NotNull
    private String codeTransporteur;
}
