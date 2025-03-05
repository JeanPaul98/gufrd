package com.acl.formalitesanteapi.dto.inspection.desinfection;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Schema(description ="DemandeDesinfectionListDto" )
public class DemandeDesinfectionListDto {

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
    private double  tonnage;

    @Schema(description = "JPA Formalite: Destination",
            name="cargaison",
            required=true)
    @NotNull
    private double  cargaison;


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
    private int opDesinfection ;

    @Schema(description = "JPA Formalite: Opération de dératisation",
            name="opDeratisation",
            required=true)
    @NotNull
    private int  opDeratisation  ;

    @Schema(description = "JPA Formalite: Opération de désinsectisation",
            name="opDesinsectisation",
            required=true)
    @NotNull
    private int opDesinsectisation;


    /*
    Autre
     */

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
