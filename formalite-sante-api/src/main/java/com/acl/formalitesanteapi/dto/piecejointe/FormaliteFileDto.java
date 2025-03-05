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
@Schema(description ="FormaliteFileDto" )
public class FormaliteFileDto {

    /*
     * Debut Fichier
     */
    @Schema(description = "Données pour la création des fichiers",
            name="filename",
            required=true)
    @NotNull
    private String  filename;

    /*
     * Fin Fichier
     */

    /*
     * Debut Type Jointe
     */
    @Schema(description = "JPA Type Jointe",
            name="idTypePieceJointe",
            required=true)
    @NotNull
    private Long idTypePieceJointe;

    /*
     * Fin Type Jointe
     */
}
