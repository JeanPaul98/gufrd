package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "DECLARANT", schema = "VBSUSER")
public class Declarant implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_DECLARANT")
    @SequenceGenerator(sequenceName = "S_VBS_DECLARANT", allocationSize = 1, name = "S_VBS_DECLARANT")
    private Long id;

    @Column(name = "MAISON_TRANSIT", nullable = false, length = 50)
    private String maisonTransit;

    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;

    @Column(name = "MAIL", nullable = false, length = 50)
    private String mail;

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