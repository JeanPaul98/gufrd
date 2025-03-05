package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Zansouyé on 08/10/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="TraiterDemandeAutorisationAgrement" )
public class TraiterDemandeAutorisationAgrement {

    /*
    * Debut DmdAutorisationAgrement
     */
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

    /*
     * Fin DmdAutorisationAgrement
     */

    /*
    Piece jointe
    */
    List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;
    /*
    Fin de piece jointe
     */
}
