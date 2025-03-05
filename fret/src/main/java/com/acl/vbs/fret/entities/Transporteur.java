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
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TRANSPORTEUR", schema = "VBSUSER")
public class Transporteur implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_TRANSPORTEUR")
    @SequenceGenerator(sequenceName = "S_VBS_TRANSPORTEUR", allocationSize = 1, name = "S_VBS_TRANSPORTEUR")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_GARANT")
    private Garant garant;

    @Column(name = "RAISON_SOCIALE", nullable = false, length = 50)
    private String raisonSociale;

    @Column(name = "TELEPHONE", nullable = false, length = 50)
    private String telephone;

    @Column(name = "ADRESSE", nullable = false, length = 50)
    private String adresse;

    @Column(name = "MAIL", nullable = false, length = 50)
    private String mail;

    @Column(name = "NIF", nullable = false, length = 50)
    private String nif;

    @Column(name = "RCCM", nullable = false, length = 50)
    private String rccm;

    @Column(name = "LICENCE_TRANSPORT", nullable = false, length = 50)
    private String licenceTransport;

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