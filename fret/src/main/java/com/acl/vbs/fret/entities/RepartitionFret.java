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
@Table(name = "REPARTITION_FRET", schema = "VBSUSER")
public class RepartitionFret implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_REPARTITION_FRET")
    @SequenceGenerator(sequenceName = "S_VBS_REPARTITION_FRET", allocationSize = 1, name = "S_VBS_REPARTITION_FRET")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_AFFRETEMENT", nullable = false)
    private Affretement affretement;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "QUANTITE_RESERVEE", nullable = false, length = 50)
    private String quantiteReservee;

    @Column(name = "QUANTITE_AFFECTEE", nullable = false, length = 50)
    private String quantiteAffectee;

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