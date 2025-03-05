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
@Table(name = "CAMION", schema = "VBSUSER")
public class Camion implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_CAMION")
    @SequenceGenerator(sequenceName = "S_VBS_CAMION", allocationSize = 1, name = "S_VBS_CAMION")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_TRANSPORTEUR")
    private Transporteur transporteur;

    @Column(name = "IMMATRICULATION", nullable = false, length = 50)
    private String immatriculation;

    @Column(name = "CHARGE_UTILE", nullable = false)
    private Long chargeUtile;

    @Column(name = "CAPACITE_AUTORISEE_14", nullable = false)
    private Long capaciteAutorisee14;

    @Column(name = "DATE_EXP_VISITE_TECHNIQUE", nullable = false)
    private Date dateExpVisiteTechnique;

    @Column(name = "DATE_EXP_ASSURANCE", nullable = false)
    private Date dateExpAssurance;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "SILHOUETTE", nullable = false, length = 50)
    private String silhouette;

    @Column(name = "MARQUE", nullable = false, length = 50)
    private String marque;

    @Column(name = "MODELE_CARTE_GRISE", nullable = false, length = 50)
    private String modeleCarteGrise;

    @Column(name = "NUMERO_CARTE_GRISE", nullable = false, length = 50)
    private String numeroCarteGrise;

    @Column(name = "NUMERO_CARTE_TRANSPORT", nullable = false, length = 50)
    private String numeroCarteTransport;

    @Column(name = "DISPONIBLE", nullable = false)
    private Boolean disponible;

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