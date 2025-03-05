package com.acl.formalitesanteapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jean paul 24/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="CategorieTypePieceDto" )
public class CategorieTypePieceDto {

    @Schema(description = "JPA Type Categorie type Piece Jointe: Information Categorie type Piece Jointe",
            name="libelle",
            required=true)
    @NotNull
    private String libelle;

    @Schema(description = "JPA Type Categorie type Piece Jointe: Information Categorie type Piece Jointe",
            name="ref",
            required=true)
    @NotNull
    private String ref;
}
