package com.acl.vbs.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PaiementRequest implements Serializable {
    @NotNull
    private String numBonEntree;

    @NotNull
    private String type;

    @NotNull
    private BigDecimal montant;

    @NotNull
    private String compteClient;

    @NotNull
    private String transactionId;
}
