package com.acl.vbs.fret.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class PaiementRequest implements Serializable {
    @NotNull
    private Long declaration;

    @NotNull
    private String type;

    @NotNull
    private double  montant;

    @NotNull
    private String compteClient;

    @NotNull
    private String transactionId;
}
