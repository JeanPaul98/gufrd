package com.acl.formaliteagricultureapi.dto.procesVerbal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Zansouyé on 26/08/2024
 * @project formalite-agriculture-api
 */

@Getter
@Setter
@Schema(description = "InspecteurDto")
public class InspecteurDto {

    @Schema(description = "JPA Formalite: Information Inspecteur",
            name="nomInspecteur",
            required=true)
    @NotNull
    private String nomInspecteur;

    @Schema(description = "JPA Formalite: Information Inspecteur",
            name="prenomsInspecteur",
            required=true)
    @NotNull
    private String prenomsInspecteur;

    @Schema(description = "JPA Formalite: Information Inspecteur",
            name="numero",
            required=true)
    @NotNull
    private String numero;

    @Schema(description = "JPA Formalite: Information Inspecteur",
            name="fonction",
            required=true)
    @NotNull
    private String fonction;
}
