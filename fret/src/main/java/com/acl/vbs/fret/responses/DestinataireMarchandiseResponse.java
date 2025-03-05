package com.acl.vbs.fret.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DestinataireMarchandiseResponse implements Serializable {
    private Long id;
    private String nom;
    private String typeDestinataire;
    private String telephone;
    private String natureMarchandise;
    private String codeSh;
    private Long nombreProduit;
    private Long produitGrandeConsommation;
    private String createdBy;
    private Date createdAt;
    private String updateBy;
    private Date updateAt;
}
