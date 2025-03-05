package com.acl.formaliteenvironnementapi.dto.piecejointe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@Schema(description ="DetCategorieTypeJointeDto" )
public class DetCategorieTypeJointeDto {

    /*
     * Debut Categorie Type Piece jointe
     */
    @Schema(description = "JPA Categorie Type Piece Jointe: Information Categorie type Piece Jointe",
            name="idCategorieTypePiece",
            required=true)
    private Long idCategorieTypePiece;

    /*
     * Fin Categorie Type Piece Jointe
     */

    /*
    *Debut TypePieceJointeDto
     */

        private List<TypePieceJointeDto> typePieceJointes;
    /*
    *Fin TypePieceJointeDto
     */
}
