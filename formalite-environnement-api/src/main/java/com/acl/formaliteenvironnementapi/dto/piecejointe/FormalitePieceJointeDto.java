package com.acl.formaliteenvironnementapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jean paul 24/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="FormalitePieceJointeDto" )
public class FormalitePieceJointeDto {

    /**
     * Debut Formalite
     */

    @Schema(description = "JPA Formalite: Information sur l'id Formalité'",
            name = "idFormalite",
            required = true)
    @NotNull
    private Long idFormalite;

    /**
     * Fin Formalite
     */


    /*
     * Debut Fichier
     */
    @Schema(description = "Données pour la création des fichiers",
            name="file",
            required=true)
    @NotNull
    private MultipartFile[] file;

    @Schema(description = "Information sur la liste des pièces jointes et fichier",
            name = "formaliteFileDtoList",
            required = false)
    List<FormaliteFileDto> formaliteFileDtoList;

    @Schema(description = "Information sur la liste des pièces jointes et fichier",
            name = "filenameTypePieceJointe",
            required = true)
    @NotNull
    private String filenameTypePieceJointe;

}
