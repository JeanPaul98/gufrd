package com.acl.formaliteagricultureapi.dto.agrement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description ="AgrementBySociete" )
public class AgrementBySocieteDemandeDto {

    /*
    * Debut Societe
     */
    @Schema(description = "JPA Societe: nif",
            name="nif",
            required=true)
    @NotNull
    private String nif;
    /*
     * Fin Societe
     */

}
