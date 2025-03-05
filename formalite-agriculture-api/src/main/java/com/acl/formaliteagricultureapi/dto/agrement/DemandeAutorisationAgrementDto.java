package com.acl.formaliteagricultureapi.dto.agrement;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.models.enumeration.Etat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 26/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description ="AutorisationAgrementDto" )
public class DemandeAutorisationAgrementDto {

    /*
    * Debut DmdAutorisationAgrement
     */
    @Schema(description = "JPA Agrement: numero",
            name="numero")
    private String numero;

    @Schema(description = "JPA Agrement: activite",
            name="activite",
            required=true)
    @NotNull
    private String activite;


    @Schema(description = "JPA Agrement: observation",
            name="observation")
    private String observation;

    @Schema(description = "JPA Agrement: dateAgrement",
            name="dateAgrement",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAgrement;

    @Schema(description = "JPA Agrement: dateExpiration",
            name="dateExpiration",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpiration;

    @Schema(description = "JPA Agrement: compteClient user demandeur",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    /*
    * Fin DmdAutorisationAgrement
     */

    /*
    * Debut Societe
     */
    @Schema(description = "JPA Societe: idSociete",
            name="idSociete",
            required=true)
    @NotNull
    private Long idSociete;
    /*
    * Fin Societe
     */

    /*
    * Debut Agrement
     */
    private  Long idAgrement;
    /*
    * Fin Agrement
     */

    /*
    Piece jointe
    */
    List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;
    /*
    Fin de piece jointe
     */
}
