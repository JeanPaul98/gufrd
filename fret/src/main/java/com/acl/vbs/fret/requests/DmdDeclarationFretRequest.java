package com.acl.vbs.fret.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class DmdDeclarationFretRequest implements Serializable {

    @NotNull
    @NotBlank
    private String sensTrafic;

    @NotNull
    @NotBlank
    private String modeTransport;

    @NotNull
    @NotBlank
    private String origineMarchandise;

    @NotNull
    @NotBlank
    private String blNumeroConnaissement;

    @NotNull
    @NotBlank
    private String portEmbarquement;

    @NotNull
    @NotBlank
    private String nomNavire;

    @NotNull
    @NotBlank
    private String nationaliteNavire;

    @NotNull
    private long nombreCamionsSouhaite;

    @NotNull
    private long prixTransportSouhaiteTonne;

    @NotNull
    private boolean affichagePrixRecepisse;

    @NotNull
    @NotBlank
    private String paysProvenance;

    @NotNull
    @NotBlank
    private String villeProvenance;

    @NotNull
    @NotBlank
    private String paysDestination;

    @NotNull
    @NotBlank
    private String villeDestination;

    @NotNull
    @NotBlank
    String chargeur;

    @NotNull
    Long destinataireMarchandise;
}
