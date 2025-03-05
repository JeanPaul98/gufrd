package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GarantResponse implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String genre;
    private String mail;
    private String cin;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
