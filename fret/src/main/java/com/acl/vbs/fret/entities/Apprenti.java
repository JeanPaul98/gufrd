package com.acl.vbs.fret.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "APPRENTI", schema = "VBSUSER")
public class Apprenti implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_APPRENTI")
    @SequenceGenerator(sequenceName = "S_VBS_APPRENTI", allocationSize = 1, name = "S_VBS_APPRENTI")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_CONDUCTEUR", nullable = false)
    private Conducteur conducteur;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOMS", nullable = false, length = 50)
    private String prenoms;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "DATE_NAISSANCE", nullable = false, length = 50)
    private String dateNaissance;

    @Column(name = "GENRE", nullable = false, length = 50)
    private String genre;

    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;

    @Column(name = "ADRESSE", nullable = false, length = 50)
    private String adresse;

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