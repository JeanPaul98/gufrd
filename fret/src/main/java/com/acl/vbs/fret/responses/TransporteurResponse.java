package com.acl.vbs.fret.responses;

import com.acl.vbs.fret.entities.Garant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TransporteurResponse implements Serializable {
    private Long id;
    private GarantResponse garant;
    private String raisonSociale;
    private String telephone;
    private String adresse;
    private String mail;
    private String nif;
    private String rccm;
    private String licenceTransport;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
