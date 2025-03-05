package com.acl.vbs.fret.responses;

import com.acl.vbs.fret.entities.DmdDeclarationFret;
import com.acl.vbs.fret.entities.TypePiece;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PieceJustificativeResponse implements Serializable {
    private Long id;
    private DmdDeclarationFretResponse declarationFret;
    private TypePieceResponse typePiece;
    private String libelle;
    private String fichier;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
