package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChargeurResponse implements Serializable {
    private Long id;
    private String nom;
    private String nationalite;
    private String telephone;
    private String mail;
    private Boolean repartitionFret;
    private String compteClient;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
