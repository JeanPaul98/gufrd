package com.acl.formaliteenvironnementapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jean paul 24/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="PieceJointeDto" )
public class PieceJointeDto {

    /*
     * Fin Fichier
     */

    /*
     * Debut Type Jointe
     */
    @Schema(description = "JPA Type Jointe",
            name="typePieceJointe",
            required=true)
    @NotNull
    private String typePieceJointe;


    @Schema(description = "JPA Type Jointe",
            name="nomOriginePieceJointe",
            required=true)
    @NotNull
    private String nomOriginePieceJointe;


    @Schema(description = "JPA Type Jointe",
            name="urlPj",
            required=true)
    @NotNull
    private String urlPj;


}
