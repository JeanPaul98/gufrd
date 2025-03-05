/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MANIFESTE")
public class Manifeste implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_MANIFESTE")
    private String codeManifeste;
    @Basic(optional = false)
    @Column(name = "MANIFESTE_ADMINISTRATIF")
    private short manifesteAdministratif;
    @Basic(optional = false)
    @Column(name = "DAD")
    private short dad;
    @Column(name = "REF_MANIFESTE")
    private String refManifeste;
    @JoinColumn(name = "ID_ANNONCE_ESCALE", referencedColumnName = "ID_ANNONCE_ESCALE")
    @ManyToOne(optional = false)
    private AnnonceEscale annonceEscale;
    @JoinColumn(name = "CONSIGNATAIRE", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Consignataire consignataire;
    @JoinColumn(name = "CODE_SENS_TRAFIC", referencedColumnName = "CODE_SENS_TRAFIC")
    @ManyToOne(optional = false)
    private SensTrafic sensTrafic;
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeManifeste != null ? codeManifeste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manifeste)) {
            return false;
        }
        Manifeste other = (Manifeste) object;
        if ((this.codeManifeste == null && other.codeManifeste != null) || (this.codeManifeste != null && !this.codeManifeste.equals(other.codeManifeste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Manifeste[ codeManifeste=" + codeManifeste + " ]";
    }
    
}
