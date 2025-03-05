package com.acl.formalitesanteapi.dto.inspection.navire;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */
@Getter
@Setter
@Schema(description ="InspectionNavireDto" )
public class InspectionNavireDto {

    /*
     * Debut Formalite
     */
    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire",
            required=true)
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo",
            required=true)
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur")
    private String affreteur;

    @Schema(description = "JPA Formalite: Personne connectée",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Destination",
            name="destination",
            required=true)
    @NotNull
    private String destination;

    @Schema(description = "JPA Formalite: Provenance",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;


    @Schema(description = "JPA Formalite: Provenance",
            name="commandant",
            required=true)
    @NotNull
    private String commandant;

    @Schema(description = "JPA Formalite: Provenance",
            name="nationalite",
            required=true)
    @NotNull
    private String nationalite;

    @Schema(description = "JPA Formalite: date de la declaration",
            name="dateDeclaration",
            required=true)
    @NotNull
    private Date dateDeclaration;
}
