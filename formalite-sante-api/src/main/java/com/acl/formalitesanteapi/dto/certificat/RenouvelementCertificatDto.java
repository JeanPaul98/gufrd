package com.acl.formalitesanteapi.dto.certificat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 08/09/2024
 * @project formalite-sante-api
 */

@Getter
@Setter
@Schema(description ="RenouvelementCertificatDto" )
public class RenouvelementCertificatDto {

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
}