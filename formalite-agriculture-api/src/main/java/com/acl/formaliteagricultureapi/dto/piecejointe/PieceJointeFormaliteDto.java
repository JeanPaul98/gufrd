package com.acl.formaliteagricultureapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 05/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="PieceJointeDto" )
@ToString
public class PieceJointeFormaliteDto {

    /*
     * Fin Fichier
     */

    /*
     * Debut Type Jointe
     */
    @Schema(description = "Jpa : id du type piece jointe",
            name="idTypePieceJointe",
            required=true)
    @NotNull
    private Long idTypePieceJointe;


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
