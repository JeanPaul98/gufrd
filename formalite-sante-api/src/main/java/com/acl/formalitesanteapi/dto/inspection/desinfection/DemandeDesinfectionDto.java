package com.acl.formalitesanteapi.dto.inspection.desinfection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 09/09/2024
 * @project formalite-sante-api
 */
@Getter
@Setter
@Schema(description ="DemandeDesinfectionDto" )
public class DemandeDesinfectionDto {

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

    /*
    Fin formalite
     */

    @Schema(description = "JPA Formalite: Destination",
            name="pavillon",
            required=true)
    @NotNull
    private String pavillon;

    @Schema(description = "JPA Formalite: Destination",
            name="nrt",
            required=true)
    @NotNull
    private String nrt;

    @Schema(description = "JPA Formalite: Destination",
            name="tonnage",
            required=true)
    @NotNull
    private int  tonnage;

    @Schema(description = "JPA Formalite: Destination",
            name="cargaison",
            required=true)
    @NotNull
    private int  cargaison;


    @Schema(description = "JPA Formalite: Provenance",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA Formalite: Date de reinspection",
            name="reinspection",
            required=true)
    @NotNull
    private Date  reinspection;


    @Schema(description = "JPA Formalite: Remarque",
            name="remarque",
            required=true)
    @NotNull
    private String remarque;

    @Schema(description = "JPA Formalite: Opération de Désinfection",
            name="opDesinfection",
            required=true)
    @NotNull
    private boolean opDesinfection ;

    @Schema(description = "JPA Formalite: Opération de dératisation",
            name="opDeratisation",
            required=true)
    @NotNull
    private boolean opDeratisation  ;

    @Schema(description = "JPA Formalite: Opération de désinsectisation",
            name="opDesinsectisation",
            required=true)
    @NotNull
    private boolean opDesinsectisation;





}
