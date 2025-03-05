package com.acl.formalitesanteapi.dto.certificat;

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
public class RenouvelementCertificatListDto {

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
    Fin de formalite
     */

    /*
    Debut certificat
     */

    @Schema(description = "JPA Formalite: Certificat a renouveler",
            name="nuemroCerficatRenouveler",
            required=true)
    @NotNull
    private String nuemroCerficatRenouveler;

    /*
    Fin certificat
     */

    /*
    Donner de liste
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

    private Long idCertificat;

    @Schema(description = "JPA Formalite: Personne connectée",
            name="compteClient",
            required=true)
    @NotNull
    private String numGenerer;

}
