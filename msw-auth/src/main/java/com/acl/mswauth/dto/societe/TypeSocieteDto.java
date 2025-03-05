package com.acl.mswauth.dto.societe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jules on 24/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class TypeSocieteDto {

    @Schema(description = "JPA Type Societe: ref",
            name="ref",
            required=true)
    @NotNull
    private String ref;

    @Schema(description = "JPA Type Societe: libelle",
            name="libelle")
    private String libelle;
}
