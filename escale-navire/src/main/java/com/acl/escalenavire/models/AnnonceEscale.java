package com.acl.escalenavire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ANNONCE_ESCALE")
public class AnnonceEscale implements Serializable {
    @Id
    @Column(name = "ID_ANNONCE_ESCALE", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PORT_PROVENANCE")
    private Port portProvenance;

    @ManyToOne
    @JoinColumn(name = "PORT_DESTINATION")
    private Port portDestination;

    @ManyToOne
    @JoinColumn(name = "ID_NAVIRE")
    private Navire idNavire;


    @Column(name = "NOM_AFFRETEUR_DEPART")
    private String nomAffreteurDepart;


    @Column(name = "ADRESSE_AFFRETEUR_DEPART")
    private String adresseAffreteurDepart;


    @Column(name = "NUMERO_AAN")
    private String numeroAan;

    @Column(name = "NUMERO_VOYAGE")
    private String numeroVoyage;

    @Column(name = "NUMERO_ESCALE")
    private String numeroEscale;

    @Column(name = "ETA")
    private LocalDate eta;

    @Column(name = "ETD")
    private LocalDate etd;

    @Column(name = "TIRANT_EAU_AVANT")
    private BigDecimal tirantEauAvant;

    @Column(name = "TIRANT_EAU_ARRIERE")
    private BigDecimal tirantEauArriere;

    @Column(name = "DATE_ENTREE_PREV")
    private LocalDate dateEntreePrev;


    @Column(name = "DESCRIPTION_CARGAISON")
    private String descriptionCargaison;

    @Column(name = "NOM_CDT")
    private String nomCdt;

    @Column(name = "DATE_ARRIVEE_RADE")
    private LocalDate dateArriveeRade;

    @Column(name = "DATE_SORTIE_RADE")
    private LocalDate dateSortieRade;

    @Column(name = "DATE_CLOTURE_ESCALE")
    private LocalDate dateClotureEscale;

    @Column(name = "DATE_ACCOSTAGE")
    private LocalDate dateAccostage;

    @Column(name = "DATE_ANNULATION")
    private LocalDate dateAnnulation;


    @Column(name = "OBJET_ESCALE")
    private String objetEscale;

    @Column(name = "DATE_SAISIE_CLOTURE")
    private LocalDate dateSaisieCloture;

    @Column(name = "NOM_AFFRETEUR_ARRIVEE")
    private String nomAffreteurArrivee;

    @Column(name = "ADRESSE_AFFRETEUR_ARRIVEE")
    private String adresseAffreteurArrivee;

    @Column(name = "DATE_CLOTURE_DC")
    private LocalDate dateClotureDc;

    @Column(name = "DATE_ANNONCE")
    private LocalDate dateAnnonce;

    @Column(name = "DATE_DERNIERE_DMD_FACT")
    private LocalDate dateDerniereDmdFact;

    @Column(name = "DATE_APPAREILLAGE")
    private LocalDate dateAppareillage;

    @Column(name = "DATE_CLOTURE_COM")
    private LocalDate dateClotureCom;

    @Column(name = "DATE_CLOTURE_TRANSBO")
    private LocalDate dateClotureTransbo;

    @Column(name = "DATE_DEBUT_OPERATION")
    private LocalDate dateDebutOperation;

    @Column(name = "DATE_FIN_OPERATION")
    private LocalDate dateFinOperation;

    @Column(name = "NOMBRE_TOTAL_PASSAGER_DEBARQUE")
    private Long nombreTotalPassagerDebarque;

    @Column(name = "NOMBRE_TOTAL_PASSAGER_EMBARQUE")
    private Long nombreTotalPassagerEmbarque;


    @Column(name = "PROPULSEUR_PROU_OK")
    private Boolean propulseurProuOk = false;


    @Column(name = "PROPULSEUR_POUPE_OK")
    private Boolean propulseurPoupeOk = false;


    @Column(name = "PROPULSEUR_TRIBORD_OK")
    private Boolean propulseurTribordOk = false;


    @Column(name = "PROPULSEUR_BABORD_OK")
    private Boolean propulseurBabordOk = false;

    @Column(name = "NUM_ESCALE_MENSUEL")
    private String numEscaleMensuel;


    @Column(name = "NUM_ESCALE_HISTORIQUE")
    private String numEscaleHistorique;

    @Column(name = "DATE_SUPPRESSION")
    private LocalDate dateSuppression;


    @Column(name = "CABOTEUR")
    private Boolean caboteur = false;

    @Column(name = "DEMANDE_FACTURE")
    private Long demandeFacture;

    @Column(name = "ETAT")
    private Boolean etat;

    @Column(name = "RAPPORT_ESCALE")
    private Boolean rapportEscale;

    @Column(name = "RSP")
    private Boolean rsp;

    @Column(name = "FACTURE_RSP")
    private Boolean factureRsp;

}