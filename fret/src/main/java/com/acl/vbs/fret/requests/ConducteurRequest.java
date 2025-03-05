package com.acl.vbs.fret.requests;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ConducteurRequest implements Serializable {

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

}
