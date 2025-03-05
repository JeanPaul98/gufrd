package com.acl.vbs.entities;

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
@Table(name = "VAS")
public class Va {
    @Id
    @Column(name = "ID_VAS", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CODE_AIRE_STOCKAGE", nullable = false)
    private AireStockage codeAireStockage;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_ANNONCE_ESCALE", nullable = false)
    private AnnonceEscale idAnnonceEscale;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CONSIGNATAIRE", nullable = false)
    private Consignataire consignataire;

    @NotNull
    @Column(name = "DATE_VAS", nullable = false)
    private LocalDate dateVas;

    @NotNull
    @Column(name = "QTE_VAS", nullable = false)
    private Integer qteVas;

    @Size(max = 200)
    @Column(name = "OBSERVATION_VAS", length = 200)
    private String observationVas;

    @Size(max = 15)
    @Column(name = "PLOMB", length = 15)
    private String plomb;

    @NotNull
    @ColumnDefault("to_date('23/12/2015','dd/mm/yyyy')")
    @Column(name = "DATE_SAISIE_VAS", nullable = false)
    private LocalDate dateSaisieVas;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("5501")
    @JoinColumn(name = "ID_LIGNE_MSE")
    private LigneMse idLigneMse;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "POIDS_BRUT", nullable = false, precision = 15, scale = 2)
    private BigDecimal poidsBrut;

    @Size(max = 30)
    @Column(name = "NUM_CHASSIS_VAS", length = 30)
    private String numChassisVas;

    @Size(max = 30)
    @Column(name = "NUM_CONTENEUR_VAS", length = 30)
    private String numConteneurVas;

}