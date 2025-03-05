package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CONDUCTEUR", schema = "VBSUSER")
public class Conducteur implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_CONDUCTEUR")
    @SequenceGenerator(sequenceName = "S_VBS_CONDUCTEUR", allocationSize = 1, name = "S_VBS_CONDUCTEUR")
    private Long id;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOMS", nullable = false, length = 50)
    private String prenoms;

    @Column(name = "NUMERO_TELEPHONE", nullable = false, length = 50)
    private String numeroTelephone;

    @Column(name = "NUMERO_PERMIS", nullable = false, length = 50)
    private String numeroPermis;

    @Column(name = "DATE_ETABLISSEMENT_PERMIS", nullable = false)
    private Date dateEtablissementPermis;

    @Column(name = "LIEU_ETABLISSEMENT_PERMIS", nullable = false, length = 50)
    private String lieuEtablissementPermis;

    @Column(name = "ASSOCIATION_AFFILIATION", nullable = false, length = 50)
    private String associationAffiliation;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "DATE_NAISSANCE", nullable = false, length = 50)
    private String dateNaissance;

    @Column(name = "GENRE", nullable = false, length = 50)
    private String genre;

    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "UPDATE_BY", length = 50)
    private String updateBy;

    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @PrePersist
    public void setCreatedDate() {
        this.createdAt = this.updateAt = new Date();
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updateAt = new Date();
    }

}