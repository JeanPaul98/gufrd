package com.acl.formaliteagricultureapi.dto.agrement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 22/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
public class AgrementBySocieteReturnDto {

    /*
    * Debut Agrement
     */
    @Schema(description = "JPA Agrement: numero",
            name="numero",
            required=true)
    @NotNull
    private String numero;

    @Schema(description = "JPA Agrement: activite",
            name="activite",
            required=true)
    @NotNull
    private String activite;

    @Schema(description = "JPA Agrement: dateAgrement",
            name="dateAgrement",
            required=true)
    @NotNull
    private Date dateAgrement;

    /*
    * Fin Agrement
     */

    /*
    * Debut Societe
     */

    @Schema(description = "JPA Societe: raisonSocialSociete",
            name="raisonSocialSociete",
            required=true)
    @NotNull
    private String raisonSocialSociete;

    @Schema(description = "JPA Societe: nif",
            name="nif",
            required=true)
    @NotNull
    private String nif;

    /*
    * Fin Societe
     */

    public AgrementBySocieteReturnDto(String numero, String activite, Date dateAgrement, String raisonSocialSociete,
                                      String nif) {
        this.numero = numero;
        this.activite = activite;
        this.dateAgrement = dateAgrement;
        this.raisonSocialSociete = raisonSocialSociete;
        this.nif = nif;
    }
}
