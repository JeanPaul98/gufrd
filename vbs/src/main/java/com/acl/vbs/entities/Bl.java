/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BL")
public class Bl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BL")
    private Long idBl;
    @Basic(optional = false)
    @Column(name = "NUM_BL")
    private String numBl;
    @Column(name = "DATE_ENVOI_BL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoiBl;
    @Column(name = "DATE_ABANDON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAbandon;
    @Column(name = "DESCRIPTION_BL")
    private String descriptionBl;
    @Column(name = "NOM_PROPRIETAIRE_INCONNU")
    private String nomProprietaireInconnu;
    @Column(name = "NOM_REPRESENTANT")
    private String nomRepresentant;
    @Column(name = "DEPOTAGE")
    private Integer depotage;
    @Basic(optional = false)
    @Column(name = "DMDFACTR")
    private int dmdfactr;

    @JsonIgnore
    @JoinColumn(name = "ID_BL_PARENT", referencedColumnName = "ID_BL")
    @ManyToOne
    private Bl bl;

    @JsonIgnore
    @JoinColumn(name = "ID_ARMATEUR", referencedColumnName = "ID_ARMATEUR")
    @ManyToOne(optional = false)
    private Armateur armateur;

    @JsonIgnore
    @JoinColumn(name = "AUTRECLIENT", referencedColumnName = "ID_CLIENT")
    @ManyToOne
    private AutreClient autreClient;

    @JsonIgnore
    @JoinColumn(name = "TRANSITAIRE", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Transitaire transitaire;

    @JsonIgnore
    @JoinColumn(name = "PROPRIETAIRE", referencedColumnName = "ID_CLIENT_FINAL")
    @ManyToOne(optional = false)
    private ClientFinal clientFinal;

    @JsonIgnore
    @JoinColumn(name = "CODE_MANIFESTE", referencedColumnName = "CODE_MANIFESTE")
    @ManyToOne(optional = false)
    private Manifeste manifeste;

    @JsonIgnore
    @JoinColumn(name = "CODE_MOTIF_CHANGE_DEST", referencedColumnName = "CODE_MOTIF_CHANGE_DEST")
    @ManyToOne
    private MotifChangeDest motifChangeDest;

    @JsonIgnore
    @JoinColumn(name = "PAYS_DEST", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays pays;

    @JsonIgnore
    @JoinColumn(name = "PORT_DEST", referencedColumnName = "CODE_PORT")
    @ManyToOne(optional = false)
    private Port port;

    @JsonIgnore
    @JoinColumn(name = "PORT_ORIGINE", referencedColumnName = "CODE_PORT")
    @ManyToOne(optional = false)
    private Port port1;

    @JsonIgnore
    @JoinColumn(name = "CODE_TYPE_EXPORT", referencedColumnName = "CODE_TYPE_EXPORT")
    @ManyToOne(optional = false)
    private TypeExport typeExport;

    @JsonIgnore
    @JoinColumn(name = "CODE_REGIME_TARIFAIRE", referencedColumnName = "CODE_REGIME_TARIFAIRE")
    @ManyToOne(optional = false)
    private RegimeTarifaire regimeTarifaire;

    @JsonIgnore
    @JoinColumn(name = "CODE_SOURCE", referencedColumnName = "CODE_SOURCE")
    @ManyToOne(optional = false)
    private Source source;

    @JsonIgnore
    @JoinColumn(name = "CODE_STATUT_BL", referencedColumnName = "CODE_STATUT_BL")
    @ManyToOne(optional = false)
    private StatutBl statutBl;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBl != null ? idBl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bl)) {
            return false;
        }
        Bl other = (Bl) object;
        if ((this.idBl == null && other.idBl != null) || (this.idBl != null && !this.idBl.equals(other.idBl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Bl[ idBl=" + idBl + " ]";
    }

}
