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
import java.math.BigDecimal;
import java.util.List;


/**
 * @author Elikplim 11/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAMION")
public class Camion implements Serializable, Comparable<Camion> {
    @Id
    @Basic(optional = false)
    @Column(name = "IMMATRICULATION")
    private String immatriculation;
    @Basic(optional = false)
    @Column(name = "NOM_CONDUCTEUR")
    private String nomConducteur;
    @Basic(optional = false)
    @Column(name = "POIDS_VIDE")
    private BigDecimal poidsVide;
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays codePays;
    @JsonIgnore
    @JoinColumn(name = "CODE_TRANSPORTEUR", referencedColumnName = "CODE_TRANSPORTEUR")
    @ManyToOne(optional = false)
    private Transporteur codeTransporteur;
   /* @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "immatriculation")
    private List<BonEntreeCamion> bonEntreeCamionList;

    */
    @Serial
    private static final long serialVersionUID = 1L;

    public int hashCode() {
        int hash = 0;
        return (this.immatriculation != null) ? this.immatriculation.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.immatriculation == null && other.immatriculation != null) || (this.immatriculation != null && !this.immatriculation.equals(other.immatriculation))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getClass().getSimpleName() + " :: ID = " + this.immatriculation + " :: CONDUCTEUR = " + this.nomConducteur + " :: CODE_PAYS = " + this.codePays + " :: CODE_TRANSPORTEUR = " + this.codeTransporteur;
    }

    public int compareTo(Camion o) {
        if (o == null || o.getImmatriculation() == null)
            return 1;
        if (getImmatriculation() == null) {
            return 0;
        }
        return getImmatriculation().compareTo(o.getImmatriculation());
    }

}
