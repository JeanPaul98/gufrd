package com.acl.formaliteagricultureapi.dto.agrement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zansouyé on 07/10/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="UpdateDemandeAutorisationAgrement" )
public class UpdateDemandeAutorisationAgrement {

    @Schema(description = "JPA Demande Autorisation Agrement: Information la DmdAutorisationAgrement",
            name="idDmdAutorisationAgrement",
            required=true)
    @NotNull
    private Long idDmdAutorisationAgrement;

    @Schema(description = "JPA Demande Autorisation Agrement: Information sur la personne qui fait l'update",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;
}
