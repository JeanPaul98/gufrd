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
@Schema(description ="TypePieceJointeDto" )
public class TypePieceJointeDto {

    /**
     * Debut Type Piece Jointe
     */
    @Schema(description = "JPA Type Piece Jointe: Information Piece Jointe",
            name="libelle",
            required=true)
    @NotNull
    private String libelle;

    @Schema(description = "JPA Type Piece Jointe: Information Piece Jointe",
            name="description",
            required=false)
    private String description;

    @Schema(description = "JPA Type Piece Jointe: Information Piece Jointe",
            name="obligatoire",
            required=false)
    private boolean obligatoire;


    /**
     * Fin Type Piece Jointe
     */

    /**
     * Debut Categorie Type Piece jointe
     */
    @Schema(description = "JPA Categorie Type Piece Jointe: Information Categorie type Piece Jointe",
            name="idCategorieTypePiece",
            required=true)
    private Long idCategorieTypePiece;

    /**
     * Fin Categorie Type Piece Jointe
     */
}
