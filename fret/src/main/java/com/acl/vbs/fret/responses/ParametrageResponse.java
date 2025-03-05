package com.acl.vbs.fret.responses;

import com.acl.vbs.fret.entities.Chargeur;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ParametrageResponse implements Serializable {
    private Long id;
    private Chargeur chargeur;
    private String nationalite;
    private Long quota;
    private String createdBy;
    private String updateBy;
    private Date createdAt;
    private Date updateAt;
}
