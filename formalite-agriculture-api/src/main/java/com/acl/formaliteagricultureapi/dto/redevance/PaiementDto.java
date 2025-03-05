package com.acl.formaliteagricultureapi.dto.redevance;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 10/5/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class PaiementDto {

    @Schema(description = "JPA Formalite  : numéro dossier",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;

    @Schema(description = "JPA Formalite  : type de la demande",
            name="type",
            required=true)
    @NotNull
    private String type;

    @Schema(description = "JPA Formalite  : montant de la  demande",
            name="montant",
            required=true)
    @NotNull
    private double  montant;

    @Schema(description = "JPA Formalite  : type de la demande",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;


    @Schema(description = "JPA Formalite  : Transaction Id",
            name="transactionId")
    private String transactionId;

}
