package com.acl.formaliteagricultureapi.dto.exports.vegetaux.certificat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 03/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="DmdCerticatPhytoDto" )
public class DmdCerticatPhytoDto {

    /*
    Formalite
     */
    @Schema(description = "JPA Formalite: Personne connectée",
            name = "compteClient",
            required = true)
    @NotNull
    private String compteClient;

    /*
    Fin de Formalite
     */

    /*
    Debut certificat
     */
    @Schema(description = "JPA Formalite: Personne connectée",
            name = "nomDemandeur",
            required = true)
    @NotNull
    private String nomDemandeur;

    @Schema(description = "JPA Adresse Demandeur",
            name = "adresseDemandeur",
            required = true)
    @NotNull
    private String adresseDemandeur;

    /*
    Fin certificat
     */
}
