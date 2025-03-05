package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "DESTINATAIRE_MARCHANDISE", schema = "VBSUSER")
public class DestinataireMarchandise implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_DESTINATAIRE_MARCHANDISE")
    @SequenceGenerator(sequenceName = "S_VBS_DESTINATAIRE_MARCHANDISE", allocationSize = 1, name = "S_VBS_DESTINATAIRE_MARCHANDISE")
    private Long id;

    @Column(name = "NOM", nullable = false, length = 100)
    private String nom;

    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;

    @Column(name = "EMAIL", nullable = false, length = 20)
    private String email;

    @Column(name = "NATIONALITE", nullable = true, length = 25)
    private String nationalite;

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