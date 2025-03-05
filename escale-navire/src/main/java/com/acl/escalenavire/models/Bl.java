package com.acl.escalenavire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "BL")
public class Bl {
    @Id
    @Column(name = "ID_BL", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BL_PARENT")
    private Bl idBlParent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PORT_ORIGINE", nullable = false)
    private Port portOrigine;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PORT_DEST", nullable = false)
    private Port portDest;

    @Size(max = 45)
    @NotNull
    @Column(name = "NUM_BL", nullable = false, length = 45)
    private String numBl;

    @Column(name = "DATE_ENVOI_BL")
    private LocalDate dateEnvoiBl;

    @Column(name = "DATE_ABANDON")
    private LocalDate dateAbandon;

    @Size(max = 500)
    @Column(name = "DESCRIPTION_BL", length = 500)
    private String descriptionBl;

    @Size(max = 2000)
    @Column(name = "NOM_PROPRIETAIRE_INCONNU", length = 2000)
    private String nomProprietaireInconnu;

    @Size(max = 200)
    @Column(name = "NOM_REPRESENTANT", length = 200)
    private String nomRepresentant;

    @ColumnDefault("0")
    @Column(name = "DEPOTAGE")
    private Integer depotage;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "DMDFACTR", nullable = false)
    private Integer dmdfactr;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PAYS_DEST", nullable = false)
    private Pay paysDest;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CODE_MANIFESTE", nullable = false)
    private Manifeste codeManifeste;

}