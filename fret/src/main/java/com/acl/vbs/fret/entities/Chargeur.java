package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CHARGEUR", schema = "VBSUSER")
public class Chargeur implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_CHARGEUR")
    @SequenceGenerator(sequenceName = "S_VBS_CHARGEUR", allocationSize = 1, name = "S_VBS_CHARGEUR")
    private Long id;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;

    @Column(name = "MAIL", nullable = false, length = 50)
    private String mail;

    @Column(name = "REPARTITION_FRET", nullable = false)
    private Boolean repartitionFret;

    @Column(name = "COMPTE_CLIENT", nullable = false, length = 50)
    private String compteClient;

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