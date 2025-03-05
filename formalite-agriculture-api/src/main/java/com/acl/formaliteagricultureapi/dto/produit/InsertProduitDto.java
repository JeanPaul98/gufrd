package com.acl.formaliteagricultureapi.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertProduitDto {

    @Schema(description = "JPA Produit: code produit",
            name="code",
            required=true)
    @NotNull
    private String code;

    @Schema(description = "JPA Produit: libelle produit",
            name="libelle")
    @NotNull
    private String libelle;

    @Schema(description = "JPA Produit: Description produit",
            name="description")
    private String description;

    private long typeProduitId;

    private long structureId;

    private long varieteProduitId;
}
