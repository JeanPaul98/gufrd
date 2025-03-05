package com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zansouyé on 30/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="AutorisationEnlevementClientFileDto" )
public class AutorisationEnlevementClientFileDto {

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
