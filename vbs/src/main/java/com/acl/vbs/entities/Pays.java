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

/**
 * @author Elikplim 11/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYS")
public class Pays implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_PAYS")
    private String codePays;
    @Column(name = "INDICATIF_PAYS")
    private String indicatifPays;
    @Basic(optional = false)
    @Column(name = "NOM_PAYS")
    private String nomPays;
    @Column(name = "CAPITALE")
    private String capitale;
    @Basic(optional = false)
    @Column(name = "NATIONALITE")
    private String nationalite;
    @JsonIgnore
    @JoinColumn(name = "CODE_CONTINENT", referencedColumnName = "CODE_CONTINENT")
    @ManyToOne(optional = false)
    private Continent continent;
    @JsonIgnore
    @JoinColumn(name = "CODE_ZONE_PAYS", referencedColumnName = "CODE_ZONE_PAYS")
    @ManyToOne(optional = false)
    private ZonePays zonePays;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePays != null ? codePays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.codePays == null && other.codePays != null) || (this.codePays != null && !this.codePays.equals(other.codePays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Pays[ codePays=" + codePays + " ]";
    }

}
