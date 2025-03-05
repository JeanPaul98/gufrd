package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApprentiResponse implements Serializable {
    private Long id;
    private ConducteurResponse conducteur;
    private String nom;
    private String prenoms;
    private String nationalite;
    private String dateNaissance;
    private String genre;
    private String telephone;
    private String adresse;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
