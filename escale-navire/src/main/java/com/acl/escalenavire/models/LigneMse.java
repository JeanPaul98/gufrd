package com.acl.escalenavire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "LIGNE_MSE")
public class LigneMse {
    @Id
    @Column(name = "ID_LIGNE_MSE", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_BL", nullable = false)
    private Bl idBl;

    @Size(max = 3500)
    @Column(name = "DESC_MSE", length = 3500)
    private String descMse;

    @NotNull
    @Column(name = "POIDS_BRUT", nullable = false, precision = 15, scale = 2)
    private BigDecimal poidsBrut;

    @Size(max = 15)
    @Column(name = "PLOMB", length = 15)
    private String plomb;

    @Size(max = 20)
    @NotNull
    @ColumnDefault("'MARCHANDISE_CONV'")
    @Column(name = "TYPE_LIGNE_MSE", nullable = false, length = 20)
    private String typeLigneMse;

    @Column(name = "DATE_VAQ")
    private LocalDate dateVaq;

    @Column(name = "DATE_VAS")
    private LocalDate dateVas;

    @Column(name = "DATE_VSP")
    private LocalDate dateVsp;

    @Column(name = "DATE_VEQ")
    private LocalDate dateVeq;

    @Column(name = "DATE_VES")
    private LocalDate dateVes;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "DEPOTEE", nullable = false)
    private Boolean depotee = false;

    @Size(max = 15)
    @Column(name = "PLOMB2", length = 15)
    private String plomb2;

    @Size(max = 15)
    @Column(name = "PLOMB3", length = 15)
    private String plomb3;

    @Column(name = "DATE_VAB")
    private LocalDate dateVab;

    @Column(name = "DATE_VEP")
    private LocalDate dateVep;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOTALEMENT_SORTIE", nullable = false)
    private Boolean totalementSortie = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TRANSFEREE", nullable = false)
    private Boolean transferee = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "POIDS_DECL", nullable = false, precision = 15, scale = 2)
    private BigDecimal poidsDecl;

    @Column(name = "POIDS_NET", precision = 15, scale = 2)
    private BigDecimal poidsNet;

    @ColumnDefault("0")
    @Column(name = "PIA")
    private Boolean pia;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "SORTIE_MARITIME", nullable = false)
    private Boolean sortieMaritime = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("'CNT'")
    @JoinColumn(name = "CODE_CONDITION", nullable = false)
    private Conditionnement codeCondition;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CODE_MARCHANDISE", nullable = false)
    private Marchandise codeMarchandise;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NUM_CONTENEUR")
    private Conteneur numConteneur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("'MANIF'")
    @JoinColumn(name = "CODE_SITU_MSE", nullable = false)
    private SituationMse codeSituMse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("'FR'")
    @JoinColumn(name = "PAYS_ORIGINE", nullable = false)
    private Pay paysOrigine;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MANUTENTIONNAIRE", nullable = false)
    private Concessionnaire manutentionnaire;


}