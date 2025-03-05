package com.acl.vbs.fret.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PARAMETRAGE", schema = "VBSUSER")
public class Parametrage implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_PARAMETRAGE")
    @SequenceGenerator(sequenceName = "S_VBS_PARAMETRAGE", allocationSize = 1, name = "S_VBS_PARAMETRAGE")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_CHARGEUR")
    private Chargeur chargeur;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    private String nationalite;

    @Column(name = "QUOTA", nullable = false)
    private Long quota;

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