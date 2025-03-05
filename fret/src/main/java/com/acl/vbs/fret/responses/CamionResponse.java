package com.acl.vbs.fret.responses;

import com.acl.vbs.fret.entities.Transporteur;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CamionResponse implements Serializable {
    private Long id;
    private TransporteurResponse transporteur;
    private String immatriculation;
    private Long chargeUtile;
    private Long capaciteAutorisee14;
    private Date dateExpVisiteTechnique;
    private Date dateExpAssurance;
    private String nationalite;
    private String silhouette;
    private String marque;
    private String modeleCarteGrise;
    private String numeroCarteGrise;
    private String numeroCarteTransport;
    private Long disponible;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
