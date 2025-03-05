package com.acl.formalitesanteapi.dto.inspection.navire;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Schema(description ="InspectionNavireListDto" )
public class InspectionNavireListDto {

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


    /*
    Autre
     */

    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;


    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="etat",
            required=true)
    @NotNull
    private String etat;

    private Long idFormalite;

    private Long idInspection;

    @Schema(description = "JPA Formalite: Numéro générer",
            name="numGenerer",
            required=false)
    @NotNull
    private String numGenerer;

}
