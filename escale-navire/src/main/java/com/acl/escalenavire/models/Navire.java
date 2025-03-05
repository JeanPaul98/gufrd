package com.acl.escalenavire.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "NAVIRE")
public class Navire implements Serializable {
    @Id
    @Column(name = "ID_NAVIRE", nullable = false)
    private Long id;

    @Column(name = "NUMERO_OFFICIEL")
    private Long numeroOfficiel;

    @Column(name = "IMO")
    private String imo;


    @Column(name = "NOM_NAVIRE")
    private String nomNavire;


    @Column(name = "NUMERO_MMSI", nullable = false, length = 30)
    private String numeroMmsi;


    @Column(name = "CODE_RADIO")
    private String codeRadio;


    @Column(name = "HAUTEUR")
    private BigDecimal hauteur;

    @NotNull
    @Column(name = "JAUGE_BRUTE")
    private BigDecimal jaugeBrute;

    @NotNull
    @Column(name = "JAUGE_NETTE")
    private BigDecimal jaugeNette;

    @Column(name = "CAPACITE_TEU")
    private BigDecimal capaciteTeu;

    @Column(name = "TIRANT_EAU_PLEIN")
    private BigDecimal tirantEauPlein;

    @Column(name = "TIRANT_EAU_VIDE")
    private BigDecimal tirantEauVide;

    @Column(name = "TIRANT_EAU_ETE")
    private BigDecimal tirantEauEte;

    @Column(name = "TIRANT_AIR_NAV")
    private BigDecimal tirantAirNav;

    @Column(name = "NOMBRE_CALES")
    private Long nombreCales;

    @NotNull
    @Column(name = "LISTE_NOIRE")
    private Boolean listeNoire = false;

    @Column(name = "DEADWEIGHT")
    private BigDecimal deadweight;

    @Column(name = "VOLUME")
    private BigDecimal volume;

    @Column(name = "CAPACITE_PASSAGER")
    private Integer capacitePassager;

    @Column(name = "CAPACITE_RORO")
    private Integer capaciteRoro;

    @NotNull
    @ColumnDefault("1990")
    @Column(name = "ANNEE_CONSTRUCT", nullable = false)
    private Short anneeConstruct;

    @NotNull
    @Column(name = "PROPULSEUR_PROU", nullable = false)
    private Boolean propulseurProu = false;

    @NotNull
    @Column(name = "PROPULSEUR_POUPE", nullable = false)
    private Boolean propulseurPoupe = false;

    @Column(name = "PROPULSEUR_TRIBORD")
    private Boolean propulseurTribord;

    @Column(name = "PROPULSEUR_BABORD")
    private Boolean propulseurBabord;

    @NotNull
    @Column(name = "PUISSANCE_PROU")
    private BigDecimal puissanceProu;

    @NotNull
    @Column(name = "PUISSANCE_POUPE")
    private BigDecimal puissancePoupe;

    @Column(name = "PUISSANCE_TRIBORD")
    private BigDecimal puissanceTribord;

    @Column(name = "PUISSANCE_BABORD")
    private BigDecimal puissanceBabord;

    @Column(name = "DATE_DERNIER_CARENAGE")
    private LocalDate dateDernierCarenage;

    @Column(name = "DATE_PROCHAIN_CARENAGE")
    private LocalDate dateProchainCarenage;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "SAISI", nullable = false)
    private Boolean saisi = false;

    @Column(name = "DATE_DERNIERE_SAISIE")
    private LocalDate dateDerniereSaisie;

    @Column(name = "DATE_AJOUT_LISTE_NOIRE")
    private LocalDate dateAjoutListeNoire;

    @Column(name = "DATE_RETRAIT_LISTE_NOIRE")
    private LocalDate dateRetraitListeNoire;

    @Column(name = "LARGEUR_HT")
    private BigDecimal largeurHt;

    @Column(name = "LONGUEUR_HT")
    private BigDecimal longueurHt;

}