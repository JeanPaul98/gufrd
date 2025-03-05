package com.acl.vbs.fret.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TYPE_PIECE", schema = "VBSUSER")
public class TypePiece implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_VBS_TYPE_PIECE")
    @SequenceGenerator(sequenceName = "S_VBS_TYPE_PIECE", allocationSize = 1, name = "S_VBS_TYPE_PIECE")
    private Long id;

    @Column(name = "LIBELLE", nullable = false, length = 50)
    private String libelle;

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