package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.models.enumeration.EtatTypeAgrement;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zansouyé on 25/09/2024
 * @project formalite-agriculture-api
 */

@Getter
@Setter
@NoArgsConstructor
@Schema(description ="AgrementDto" )
public class AgrementDto {

    /*
    * Debut Agrement
     */
    @Schema(description = "JPA Agrement: libelle",
            name="libelle",
            required=true)
    @NotNull
    private String libelle;

    @Schema(description = "JPA Agrement: etatAgrement",
            name="etatAgrement",
            required=true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EtatTypeAgrement etatTypeAgrement;

    @Schema(description = "JPA Agrement: duree",
            name="duree")
    @NotNull
    private int duree;

    @Schema(description = "JPA Agrement: montant",
            name="montant",
            required=true)
    @NotNull
    private double montant;

    @Schema(description = "JPA Agrement: compteClient",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    /*
     * Fin Agrement
     */

    /*
    * Debut Structure
     */
    @Schema(description = "JPA Structure: idStructure",
            name="idStructure",
            required=true)
    @NotNull
    private Long idStructure;
    /*
    * Fin Structure
     */

}
