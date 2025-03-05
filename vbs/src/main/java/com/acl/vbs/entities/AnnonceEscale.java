/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANNONCE_ESCALE")
public class AnnonceEscale implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ANNONCE_ESCALE")
    @Column(name = "ID_ANNONCE_ESCALE")
    private Long idAnnonceEscale;
    @Column(name = "NOM_AFFRETEUR_DEPART")
    private String nomAffreteurDepart;

    @Column(name = "ADRESSE_AFFRETEUR_DEPART")
    private String adresseAffreteurDepart;

    @Basic(optional = false)
    @Column(name = "NUMERO_AAN")
    private String numeroAan;

    @Column(name = "NUMERO_VOYAGE")
    private String numeroVoyage;

    @Column(name = "NUMERO_ESCALE")
    private String numeroEscale;

    @Basic(optional = false)
    @Column(name = "ETA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eta;

    @Column(name = "ETD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date etd;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIRANT_EAU_AVANT")
    private BigDecimal tirantEauAvant;

    @Column(name = "TIRANT_EAU_ARRIERE")
    private BigDecimal tirantEauArriere;

    @Column(name = "DATE_ENTREE_PREV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntreePrev;

    @Lob
    @Column(name = "DESCRIPTION_CARGAISON")
    private String descriptionCargaison;

    @Column(name = "NOM_CDT")
    private String nomCdt;

    @Column(name = "DATE_ARRIVEE_RADE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArriveeRade;

    @Column(name = "DATE_SORTIE_RADE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortieRade;

    @Column(name = "DATE_CLOTURE_ESCALE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClotureEscale;

    @Column(name = "DATE_ACCOSTAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccostage;

    @Column(name = "DATE_ANNULATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;

    @Column(name = "OBJET_ESCALE")
    private String objetEscale;

    @Column(name = "DATE_SAISIE_CLOTURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSaisieCloture;

    @Column(name = "NOM_AFFRETEUR_ARRIVEE")
    private String nomAffreteurArrivee;

    @Column(name = "ADRESSE_AFFRETEUR_ARRIVEE")
    private String adresseAffreteurArrivee;

    @Column(name = "DATE_CLOTURE_DC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClotureDc;

    @Basic(optional = false)
    @Column(name = "DATE_ANNONCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnonce;

    @Column(name = "DATE_DERNIERE_DMD_FACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereDmdFact;

    @Column(name = "DATE_APPAREILLAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAppareillage;

    @Column(name = "DATE_CLOTURE_COM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClotureCom;
    @Column(name = "DATE_CLOTURE_TRANSBO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClotureTransbo;
    @Column(name = "DATE_DEBUT_OPERATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutOperation;
    @Column(name = "DATE_FIN_OPERATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinOperation;
    @Column(name = "NOMBRE_TOTAL_PASSAGER_DEBARQUE")
    private Long nombreTotalPassagerDebarque;
    @Column(name = "NOMBRE_TOTAL_PASSAGER_EMBARQUE")
    private Long nombreTotalPassagerEmbarque;
    @Basic(optional = false)
    @Column(name = "PROPULSEUR_PROU_OK")
    private short propulseurProuOk;
    @Basic(optional = false)
    @Column(name = "PROPULSEUR_POUPE_OK")
    private short propulseurPoupeOk;
    @Basic(optional = false)
    @Column(name = "PROPULSEUR_TRIBORD_OK")
    private short propulseurTribordOk;
    @Basic(optional = false)
    @Column(name = "PROPULSEUR_BABORD_OK")
    private short propulseurBabordOk;
    @Column(name = "NUM_ESCALE_MENSUEL")
    private String numEscaleMensuel;
    @Column(name = "NUM_ESCALE_HISTORIQUE")
    private String numEscaleHistorique;
    @Column(name = "DATE_SUPPRESSION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSuppression;
    @Basic(optional = false)
    @Column(name = "CABOTEUR")
    private short caboteur;
    @Column(name = "DEMANDE_FACTURE")
    private BigInteger demandeFacture;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnnonceEscale != null ? idAnnonceEscale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnonceEscale)) {
            return false;
        }
        AnnonceEscale other = (AnnonceEscale) object;
        if ((this.idAnnonceEscale == null && other.idAnnonceEscale != null) || (this.idAnnonceEscale != null && !this.idAnnonceEscale.equals(other.idAnnonceEscale))) {
            return false;
        }
        return true;
    }
}
