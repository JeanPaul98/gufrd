package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TypePieceResponse implements Serializable {
    private Long id;
    private String libelle;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
