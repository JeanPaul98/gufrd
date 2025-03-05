package com.acl.formaliteagricultureapi.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CERTIFICAT", schema = "USERFMA")
@Schema(implementation = Certificat.class)
public class Certificat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_CERTIFICAT")
    @SequenceGenerator(sequenceName = "S_CERTIFICAT", allocationSize = 1, name = "S_CERTIFICAT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORIGINE_PRDT")
    private String origineprdt;

    @Column(name = "EXPEDITEUR")
    private String expediteur;

    @Column(name = "POIDS_TOTAL")
    private BigInteger poidstotal;

    @Column(name = "NOMBRE")
    private BigInteger nombre;

    @Column(name = "RACE")
    private String race;

    @Column(name = "ESPECES")
    private String especes;

    @Column(name = "CONDITIONNEMENT")
    private String conditionnement;

    @Column(name = "TRAITEMENT")
    private String traitement;

    @Column(name = "IDENTIFICATION")
    private String identification;

    @Column(name = "NATURE_EMBALLAGE")
    private String natureemballage;

    @Column(name = "DATE_SIGNATURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesignature;


    @Column(name = "SIGNATURE")
    private String signature;

    @Column(name = "nomDemandeur")
    private String nomDemandeur;

    @Column(name = "adresseDemandeur")
    private String adresseDemandeur;


    @Column(name = "MOYEN_TRANSPORT")
    private String moyentransport;

    @Column(name = "NOM_EXPEDITEUR")
    private String nomexpediteur;

    @Column(name = "ADRESSE_EXPE")
    private String adresseexpe;

    @Column(name = "TYPE_IMPORTATION")
    private String typeimportation;

    @Column(name = "NOM_DESTINAT")
    private String nomdestinat;

    @Column(name = "ADRESSE_DESTINAT")
    private String adressedestinat;

    @Column(name = "PAYS_EXPEDITEUR")
    private String paysexpediteur;

    @Column(name = "PAYS_ORIGINE")
    private String paysorigine;

    @Column(name = "AUTORITE_COMPETENTE")
    private String autoritecompetente;

    @Column(name = "POSTE_FRONTALIER_PREVU")
    private String postefrontalierprevu;

    @Column(name = "VETERINAIRE")
    private String veterinaire;

    @Column(name = "PAYS_DESTINATAIRE")
    private String paysDestinataire;

    @Column(name = "LIEU_CHARGEMENT")
    private String lieuchargement;

    @Column(name = "AUTRE")
    private String autre;

    @Column(name = "DESCRIPTION_MARCHANDISE")
    private String descriptionmarchandise;

    @Column(name = "SEXE")
    private String sexe;

    @Column(name = "NOM_NAVIRE")
    private String nomnavire;

    @Column(name = "AGE")
    private String age;

    @Column(name = "QUANTITE")
    private BigInteger quantite;

    @Column(name = "NATURE_PRODUIT")
    private String natureproduit;

    @Column(name = "EMBALLEUR")
    private String emballeur;

    @Column(name = "NUMPV")
    private long numPv;

    @Column(name = "OBSERVATION")
    private String observation;

    @Column(name = "NOM_SCIENTIFIQUE")
    private String nomScientifique;

    @Column(name = "TEMPERATURE")
    private String temperature;

    @Column(name = "ACCORDER")
    private int accorder;

    @Column(name = "POINT_ENTREE")
    private String pointEntree;

    @Column(name = "MOTIF_REFUS")
    private String motifRefus;


    @Column(name = "LIEU_EXPEDITION")
    private String lieuExpedition;


    @Column(name = "LIEU_ORIGINE")
    private String lieuOrigine;


    @Column(name = "LIEU_DECHARGEMENT")
    private String lieuDechargement;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_DEPART")
    private Date dateDepart;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE_CERTIFICAT")
    private TypeCertificat typeCertificat;

    @OneToOne(mappedBy = "certificat")
    private Formalite formalite;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public Certificat(String expediteur, String destinataire, String moyenTransport,
                      String identification, String lieuExpedition,
                      String traitement) {
        this.expediteur= expediteur;
        this.nomdestinat = destinataire;
        this.moyentransport = moyenTransport;
        this.identification = identification;
        this.lieuExpedition = lieuExpedition;
        this.traitement = traitement;
    }

    public Certificat(String moyenTransport, String paysExpediteur, String expediteur,
                      String paysOrigine, String lieuOrigine, String paysDestination,
                      String nomDestinataire, String lieuChargement, Date dateDepart,
                      String posteFrontalier) {
        this.moyentransport = moyenTransport;
        this.paysexpediteur = paysExpediteur;
        this.paysorigine = paysOrigine;
        this.expediteur = expediteur;
        this.lieuOrigine = lieuOrigine;
        this.paysDestinataire = paysDestination;
        this.nomdestinat = nomDestinataire;
        this.lieuchargement = lieuChargement;
        this.dateDepart = dateDepart;
        this.postefrontalierprevu = posteFrontalier;
    }

    public Certificat(String expediteur, String adresseExpediteur,
                      String destinataire, String adresseDestinataire,
                      String lieuDestination, String lieuExpedition, String lieuDeChargement,
                      String moyenTransport) {

        this.expediteur = expediteur;
        this.adresseexpe = adresseExpediteur;
        this.nomdestinat = destinataire;
        this.adressedestinat = adresseDestinataire;
        this.paysDestinataire =lieuDestination;
        this.lieuExpedition = lieuExpedition;
        this.lieuDechargement = lieuDeChargement;
        this.moyentransport = moyenTransport;
    }

    public void setTypeCertificat(TypeCertificat typeCertificat) {
        this.typeCertificat = typeCertificat;
    }
}
