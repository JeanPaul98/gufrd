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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "REGIME_TARIFAIRE")
public class RegimeTarifaire implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_REGIME_TARIFAIRE")
    private String codeRegimeTarifaire;
    @Basic(optional = false)
    @Column(name = "LIB_REGIME_TARIFAIRE")
    private String libRegimeTarifaire;
    @Basic(optional = false)
    @Column(name = "TVA")
    private short tva;
    @Basic(optional = false)
    @Column(name = "TCC")
    private short tcc;
    @Basic(optional = false)
    @Column(name = "SMOP")
    private short smop;
    @Basic(optional = false)
    @Column(name = "CODE_ACTIVITE_TERRE")
    private String codeActiviteTerre;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeRegimeTarifaire != null ? codeRegimeTarifaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegimeTarifaire)) {
            return false;
        }
        RegimeTarifaire other = (RegimeTarifaire) object;
        if ((this.codeRegimeTarifaire == null && other.codeRegimeTarifaire != null) || (this.codeRegimeTarifaire != null && !this.codeRegimeTarifaire.equals(other.codeRegimeTarifaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.RegimeTarifaire[ codeRegimeTarifaire=" + codeRegimeTarifaire + " ]";
    }
    
}
