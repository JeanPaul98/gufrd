/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteagricultureapi.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Olivier
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PHYTOSANITAIRE", schema = "USERFMA")
@Schema(implementation = PhytoSanitaire.class)
public class PhytoSanitaire implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_PHYTOSANITAIRE")
    @SequenceGenerator(sequenceName = "S_PHYTOSANITAIRE", allocationSize = 1, name = "S_PHYTOSANITAIRE")
    @Column(name = "ID")
    private Long id;

    
    @Column(name = "NOM_EXPEDITEUR")
    private String nomExpediteur;


    @Column(name = "NOM_DEMANDEUR")
    private String nomDemandeur;
    
    @Column(name = "PROFESSION_DEMANDEUR")
    private String professionDemandeur;
    
    @Column(name = "ADRESSE_DEMANDEUR")
    private String adresseDemandeur;
    
    @Column(name = "TYPE_INSPECTION")
    private String typeInspection;

    @Column(name = "ADRESSE_EXPEDITEUR")
    private String adresseExpediteur;

    
    @Column(name = "QTE_OU_POIDS_TOTAL")
    private double qteouPoidsTotal;
    
    @Column(name = "NBE_UNITES")
    private int nbeUnites;
    
    @Column(name = "LIEU_CHARGEMENT")
    private String lieuChargement;
    
    @Column(name = "LIEU_DESTINATION")
    private String lieuDestination;
    
    @Column(name = "NOM_DESTINATAIRE")
    private String nomDestinataire;
    
    @Column(name = "CONTENEUR")
    private String conteneur;
        
    @Column(name = "ADRESSE_DESTINATAIRE")
    private String adresseDestinataire;
    
    @Column(name = "NOMBRE_COLIS")
    private int nombreColis;
    
    @Column(name = "QUANTITE")
    private double quantite;
    
    @Size(max = 254)
    @Column(name = "LIEU_ORIGINE")
    private String lieuOrigine;
    
    @Size(max = 254)
    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;
    
    @Size(max = 254)
    @Column(name = "POINT_ENTREE")
    private String pointEntree;
    
    @Column(name = "DATE_HEURE_EMBARQUEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureEmbarquement;
    
    @Size(max = 254)
    @Column(name = "NOM_SOC_TRAITEMENT")
    private String nomSocTraitement;
    
    @Size(max = 254)
    @Column(name = "CONTACT_SOCIETE")
    private String contactSociete;

    @Column(name = "TRAITEMENT_PRODUIT")
    private boolean traitementProduit;
    
    @Column(name = "RENSEIGNEMENT_COMPL")
    private String renseignementCompl;
    
    @Column(name = "ACCORDER")
    private int accorder;
    
    @Size(max = 254)
    @Column(name = "MOTIF_REFUS")
    private String motifRefus;

    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;
    
    @Column(name = "HEURE_DEB_TRAIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebTrait;
    
    @Size(max = 254)
    @Column(name = "NOM_COMMERCIAL_PRDT")
    private String nomCommercialPrdt;
    
    @Size(max = 254)
    @Column(name = "SUBSTANCE_ACTIVE")
    private String substanceActive;
    
    @Size(max = 254)
    @Column(name = "CONCENTRATION")
    private String concentration;

    @Column(name = "DUREE")
    private int duree;
    
    @Column(name = "TEMPERATURE")
    private Double temperature;
    
    @Size(max = 254)
    @Column(name = "RENSEIGN_COMPL")
    private String renseignCompl;
    
    @Size(max = 254)
    @Column(name = "DECL_SUPPL")
    private String declSuppl;
    
    @Size(max = 254)
    @Column(name = "NOM_INSPECTEUR")
    private String nomInspecteur;
    
    @Size(max = 254)
    @Column(name = "VOL")
    private String vol;
    @Size(max = 254)
    @Column(name = "NUM_CAMION")
    private String numCamion;
    @Size(max = 254)
    @Column(name = "MAGASIN_PROV")
    private String magasinProv;
    @Size(max = 254)
    @Column(name = "DATE_DELIVRANCE")
    private String dateDelivrance;
    @Size(max = 254)
    @Column(name = "LIEU_DELIVRANCE")
    private String lieuDelivrance;

    @Column(name = "DATE_SIGNATURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSignature;

    @OneToOne(mappedBy = "phytoSanitaire")
    private Formalite formalite;

    @Column(name = "DATE_PREVUE_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePrevueInspection;

    @Size(max = 254)
    @Column(name = "LIEU_INSPECTION")
    private String lieuInspection;

    @Column(name = "STRUCTURE_DEMANDEUR")
    private String structureDemandeur;

    @Column(name = "TYPE_CARGAISON")
    private String typeCargaison;

    @Column(name = "NUMERO_BL")
    private String numeroBl;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_INSPECT_PHYTO")
    private TypeInspPhyto typeInspPhyto;


    public PhytoSanitaire(String nomDemandeur, String professionDemandeur, String adresseDemandeur,
                          String lieuInspectionn) {

        this.nomDemandeur = nomDemandeur;
        this.professionDemandeur = professionDemandeur;
        this.adresseDemandeur = adresseDemandeur;
        this.lieuInspection = lieuInspection;

    }

    public PhytoSanitaire(String nomDemandeur, String professionDemandeur, String adresseDemandeur,
                          String lieuInspection,String structureDemandeur, String numeroBl, Date datePrevueInspection) {

        this.nomDemandeur = nomDemandeur;
        this.professionDemandeur = professionDemandeur;
        this.adresseDemandeur = adresseDemandeur;
        this.lieuInspection = lieuInspection;
        this.structureDemandeur = structureDemandeur;
        this.numeroBl = numeroBl;
        this.datePrevueInspection = datePrevueInspection;
    }

    public PhytoSanitaire(String nomDemandeur, String professionDemandeur, String adresseDemandeur, String lieuInspection, String structureDemandeur) {
        this.nomDemandeur = nomDemandeur;
        this.professionDemandeur = professionDemandeur;
        this.adresseDemandeur = adresseDemandeur;
        this.lieuInspection = lieuInspection;
        this.structureDemandeur = structureDemandeur;
    }
}
