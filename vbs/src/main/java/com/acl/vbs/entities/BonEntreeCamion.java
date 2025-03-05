/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author DEV
 */
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BON_ENTREE_CAMION", schema = "SIPEDBA")
public class BonEntreeCamion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_BEC")
    @Column(name = "NUM_BON_ENTREE_CAMION", nullable = false, length = 20)
    private String numBonEntreeCamion;

    @Basic(optional = false)
    @Column(name = "DATE_BON_ENTREE")
    @Temporal(TemporalType.DATE)
    private Date dateBonEntree;
    @Column(name = "REMORQUE")
    private String remorque;
    @Basic(optional = false)
    @Column(name = "NOM_CONDUCTEUR")
    private String nomConducteur;
    @Basic(optional = false)
    @Column(name = "DATE_EMISSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmission;
    @Basic(optional = false)
    @Column(name = "DATE_EFFECTIVE_ENTREE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEffectiveEntree;
    @Basic(optional = false)
    @Column(name = "DATE_SORTIE_PREVUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortiePrevue;
    @Basic(optional = false)
    @Column(name = "PENALITE_REGLEE")
    private Boolean penaliteReglee;
    @Basic(optional = false)
    @Column(name = "POIDS_VIDE")
    private BigDecimal poidsVide;
    @Basic(optional = false)
    @Column(name = "POIDS_CHARGE")
    private BigDecimal poidsCharge;
    @Basic(optional = false)
    @Column(name = "OBSERVATION_PESAGE")
    private String observationPesage;
    @Basic(optional = false)
    @Column(name = "POIDS_CHARGE_ENLEVE")
    private BigDecimal poidsChargeEnleve;
    @Basic(optional = false)
    @Column(name = "BON_CHARGEMENT")
    private String bonChargement;
    @Basic(optional = false)
    @Column(name = "CHARGEUR")
    private String chargeur;
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "TRANSPORTE_MSE")
    private Boolean transporteMse;

    @Basic(optional = false)
    @Column(name = "NUM_TICKET")
    private String numTicket;

    @Basic(optional = false)
    @Column(name = "TYPE_LIEU")
    private Boolean typeLieu;

    @Column(name = "STATUT_PAYEMENT")
    private Boolean statutPayement;

    @Column(name = "DATE_PAYEMENT")
    private Date datePayement;

    @JsonIgnore
    @JoinColumn(name = "CODE_TRANSPORTEUR", referencedColumnName = "CODE_TRANSPORTEUR")
    @ManyToOne(optional = false)
    private Transporteur codeTransporteur;

    @JsonIgnore
    @JoinColumn(name = "TRANSITAIRE", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Transitaire transitaire;

    @JsonIgnore
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays codePays;

    @JsonIgnore
    @JoinColumn(name = "IMMATRICULATION", referencedColumnName = "IMMATRICULATION")
    @ManyToOne(optional = false)
    private Camion immatriculation;

    @JsonIgnore
    @JoinColumn(name = "CODE_SENS_TRAFIC", referencedColumnName = "CODE_SENS_TRAFIC")//AJOUTER LE 25-10-2021
    @ManyToOne(optional = false)
    private SensTrafic codeSensTrafic;

    @JsonIgnore
    @JoinColumn(name = "LIEU_CHARGEMENT", referencedColumnName = "CODE_AIRE_STOCKAGE")
    @ManyToOne
    private AireStockage lieuChargement;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "bonEntreeCamion")
    @ToString.Exclude
    private List<BecLigneMse> becLigneMseList;

    @JoinColumn(name = "SITE_PESAGE", referencedColumnName = "ID_SITE")//AJOUTER LE 25-10-2021
    @ManyToOne(optional = false)
    private Site sitePesage;

    @Transient
    private String poidsMin;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Column(name = "CREATED_BY_USER")
    @Temporal(TemporalType.TIMESTAMP)
    private String createdByUser;

    @Column(name = "COMPTE_CLIENT")
    private String compteClient;

    @Column(name = "UPDATE_BY_USER")
    private String updateByUser;

    @Column(name = "STATUT_DEMANDE")
    private String statusDemande;

//    public String getPoidsMin() {
//        if (getSitePesage() != null) {
//            if (poidsCharge != null && poidsVide != null) {
//                BigDecimal resultat = poidsCharge.subtract(poidsVide);
//                if (resultat.compareTo(new BigDecimal(25)) == 1) {
//                    poidsMin = resultat.multiply(new BigDecimal(getSitePesage().getRedevance())).toString();
//                } else if ((resultat.compareTo(new BigDecimal(25)) == -1) || (resultat.compareTo(new BigDecimal(25)) == 0)) {
//                    poidsMin = "5650";
//                }
//            }
//
//        }
//
//        return poidsMin;
//    }

    @PrePersist
    public void setCreatedAt() {
        this.creationAt = this.dateBonEntree = this.dateEmission = new Date();
        this.statutPayement = false;
    }
}
