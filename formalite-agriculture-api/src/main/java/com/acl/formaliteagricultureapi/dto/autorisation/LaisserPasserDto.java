package com.acl.formaliteagricultureapi.dto.autorisation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zansouyé on 10/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description ="LaisserPasserDto" )
public class LaisserPasserDto {

    /*
    * Debut Formalite
     */
    @Schema(description = "JPA Formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;

    @Schema(description = "JPA Formalite",
            name="nomImportateur",
            required=true)
    @NotNull
    private String nomImportateur;
    /*
    *Fin Formalite
     */

    /*
    * Debut Inspecteur
     */
    @Schema(description = "JPA Inspecteur",
            name="idInspecteur",
            required=true)
    @NotNull
    private Long idInspecteur;
    /*
    * Fin Inspecteur
     */

    /*
    * Debut LaisserPasser
     */
    @Schema(description = "JPA LaisserPasser",
            name="dateInspection",
            required=true)
    @NotNull
    private Date dateInspection;

    @Schema(description = "JPA LaisserPasser",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA LaisserPasser",
            name="abattoir",
            required=true)
    @NotNull
    private String abattoir;

    @Schema(description = "JPA LaisserPasser",
            name="referenceCertificat",
            required=true)
    @NotNull
    private String referenceCertificat;

    @Schema(description = "JPA LaisserPasser",
            name="referenceConteneur",
            required=true)
    @NotNull
    private String referenceConteneur;

    @Schema(description = "JPA LaisserPasser",
            name="numeroAutorisationDepotage",
            required=true)
    @NotNull
    private String numeroAutorisationDepotage;

    @Schema(description = "JPA LaisserPasser",
            name="dateAutorisationDepotage",
            required=true)
    @NotNull
    private Date dateAutorisationDepotage;

    @Schema(description = "JPA LaisserPasser",
            name="moyenTransport",
            required=true)
    @NotNull
    private String moyenTransport;

    @Schema(description = "JPA LaisserPasser",
            name="numeroLaisserPasser",
            required=true)
    @NotNull
    private String numeroLaisserPasser;
    /*
    * Fin LaisserPasser
     */

    public LaisserPasserDto(Long idFormalite, String nomImportateur, Long idInspecteur,
                            Date dateInspection, String provenance, String abattoir,
                            String referenceCertificat, String referenceConteneur,
                            String numeroAutorisationDepotage, Date dateAutorisationDepotage,
                            String moyenTransport, String numeroLaisserPasser) {
        this.idFormalite = idFormalite;
        this.nomImportateur = nomImportateur;
        this.idInspecteur = idInspecteur;
        this.dateInspection = dateInspection;
        this.provenance = provenance;
        this.abattoir = abattoir;
        this.referenceCertificat = referenceCertificat;
        this.referenceConteneur = referenceConteneur;
        this.numeroAutorisationDepotage = numeroAutorisationDepotage;
        this.dateAutorisationDepotage = dateAutorisationDepotage;
        this.moyenTransport = moyenTransport;
        this.numeroLaisserPasser = numeroLaisserPasser;
    }
}
