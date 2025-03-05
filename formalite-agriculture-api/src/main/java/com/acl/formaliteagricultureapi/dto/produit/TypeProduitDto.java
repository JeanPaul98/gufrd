package com.acl.formaliteagricultureapi.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class TypeProduitDto {
    private Long id;
    @Schema(description = "JPA Produit: ref  sur le produit",
            name="ref",
            required=true)
    @NotNull
    private String ref;

    @Schema(description = "JPA Produit: libelle type produit",
            name="libelle")
    private String libelle;
}
