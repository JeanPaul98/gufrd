package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PORT")
public class Port implements Serializable {
    @Id
    @Column(name = "CODE_PORT")
    private String codePort;


    @Column(name = "LOCODE")
    private String locode;

    @NotNull
    @Column(name = "NOM_PORT")
    private String nomPort;


    @Column(name = "TIME_ZONE_PORT")
    private String timeZonePort;


    @Column(name = "VILLE_PORT")
    private String villePort;


    @Column(name = "CODE_VILLE_PORT")
    private String codeVillePort;


    @Column(name = "ADRESSE_PORT")
    private String adressePort;


    @Column(name = "TEL_PORT")
    private String telPort;


    @Column(name = "FAX_PORT")
    private String faxPort;


    @Column(name = "EMAIL_PORT")
    private String emailPort;


    @Column(name = "SITE_WEB_PORT")
    private String siteWebPort;

    @NotNull
    @Column(name = "LISTE_NOIRE", nullable = false)
    private Boolean listeNoire = false;

    @Column(name = "DATE_AJOUT_LISTE_NOIRE")
    private LocalDate dateAjoutListeNoire;

    @Column(name = "DATE_RETRAIT_LISTE_NOIRE")
    private LocalDate dateRetraitListeNoire;

}