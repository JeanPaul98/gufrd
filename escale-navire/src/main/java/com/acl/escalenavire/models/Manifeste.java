package com.acl.escalenavire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "MANIFESTE")
public class Manifeste {
    @Id
    @Size(max = 15)
    @Column(name = "CODE_MANIFESTE", nullable = false, length = 15)
    private String codeManifeste;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_ANNONCE_ESCALE", nullable = false)
    private AnnonceEscale idAnnonceEscale;

    @NotNull
    @Column(name = "MANIFESTE_ADMINISTRATIF", nullable = false)
    private Boolean manifesteAdministratif = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "DAD", nullable = false)
    private Boolean dad = false;

    @Size(max = 15)
    @Column(name = "REF_MANIFESTE", length = 15)
    private String refManifeste;

}