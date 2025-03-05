package com.acl.formaliteagricultureapi.dto.societe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jules on 24/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class SocieteDto {
	@Schema(description = "JPA  Societe: numeroEnreg",
            name="numeroEnreg",
            required=true)
    private String numeroEnreg;

    @Schema(description = "JPA  Societe: numéro d'identification fiscale",
            name="nif",
            required=true)
    private String nif;

    @Schema(description = "JPA  Societe: raisonSociale",
            name="raisonSociale",
            required=false)
    private String raisonSociale;

    @Schema(description = "JPA  Societe: forme Juridique",
            name="formeJuridique",
            required=false)
    private String formeJuridique;

    @Schema(description = "JPA  Societe: numRccm",
            name="numRccm")
    private String numRccm;

    @Schema(description = "JPA  Societe: adresse",
            name="adresse",
            required=false)
    private String adresse;
    
    @Schema(description = "JPA  Societe: contact",
            name="contact",
            required=false)
    private String contact;
    
    @Schema(description = "JPA  Societe: email",
            name="email")
    private String email;

    private long typeSociete;
    
}
