package com.acl.formaliteagricultureapi.dto.produit;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
public class ProduitDto {

    @Schema(description = "JPA Produit: code produit",
            name="code",
            required=true)
    @NotNull
    private String code;

    @Schema(description = "JPA Produit: libelle produit",
            name="libelle")
    private String libelle;

    @Schema(description = "JPA Produit: Description produit",
            name="description")
    private String description;
}
