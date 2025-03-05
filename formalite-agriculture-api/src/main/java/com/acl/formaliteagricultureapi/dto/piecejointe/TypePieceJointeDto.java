package com.acl.formaliteagricultureapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zansouyé on 01/09/2024
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

    public TypePieceJointeDto(String libelle, String description, boolean obligatoire) {
        this.libelle = libelle;
        this.description = description;
        this.obligatoire = obligatoire;
    }
    /**
     * Fin Type Piece Jointe
     */


}
