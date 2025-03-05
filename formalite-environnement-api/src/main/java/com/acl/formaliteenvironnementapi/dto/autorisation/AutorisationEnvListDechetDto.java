package com.acl.formaliteenvironnementapi.dto.autorisation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author kol on 11/09/2024
 * @project formalite-environnement-api
 */
@Getter
@Setter
@Schema(description ="AutorisationEnvDechetDto" )
@ToString
public class AutorisationEnvListDechetDto {

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
    Autre donnée de formalité
     */

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

    @Schema(description = "JPA formalite: Information formalite",
            name="etat",
            required=false)
    @NotNull
    private String etat;

    @Schema(description = "JPA formalite: Information formalite : Chaine (import, export)",
            name="chaine",
            required=false)
    @NotNull
    private String chaine;

    @Schema(description = "JPA formalite: Information formalite : numéro Générer)",
            name="numGenerer",
            required=false)
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA formalite: Information sur motif de rejet",
            name="motifRejet",
            required=false)
    @NotNull
    private String motifRejet;

    @Schema(description = "JPA formalite: Information sur montant Redevance",
            name="montantRedevance",
            required=false)
    @NotNull
    private double montantRedevance;



    private Long idFormalite;

    private Long idAutorisation;

}
