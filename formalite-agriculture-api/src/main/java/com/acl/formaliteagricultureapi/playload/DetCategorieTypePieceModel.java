package com.acl.formaliteagricultureapi.playload;


import com.acl.formaliteagricultureapi.dto.piecejointe.TypePieceJointeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DetCategorieTypePieceModel {
    private Long idCategorieTypePiece;
    private List<TypePieceJointeDto> typePieceJointes;
}
