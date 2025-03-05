package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ConducteurResponse implements Serializable {
    private Long id;
    private String nom;
    private String prenoms;
    private String numeroTelephone;
    private String numeroPermis;
    private Date dateEtablissementPermis;
    private String lieuEtablissementPermis;
    private String associationAffiliation;
    private String nationalite;
    private String dateNaissance;
    private String genre;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
