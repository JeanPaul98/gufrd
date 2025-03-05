package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class AffretementResponse implements Serializable {
    private Long id;
    private ConducteurResponse conducteur;
    private CamionResponse camion;
    private DmdDeclarationFretResponse declarationFret;
    private Long poidsAffecte;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
