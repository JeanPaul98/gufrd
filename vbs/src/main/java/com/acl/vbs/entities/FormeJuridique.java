/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 13/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORME_JURIDIQUE")
public class FormeJuridique implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_FORME_JURIDIQUE")
    private String codeFormeJuridique;
    @Basic(optional = false)
    @Column(name = "LIB_FORME_JURIDIQUE")
    private String libFormeJuridique;
    
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeFormeJuridique != null ? codeFormeJuridique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormeJuridique)) {
            return false;
        }
        FormeJuridique other = (FormeJuridique) object;
        if ((this.codeFormeJuridique == null && other.codeFormeJuridique != null) || (this.codeFormeJuridique != null && !this.codeFormeJuridique.equals(other.codeFormeJuridique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.FormeJuridique[ codeFormeJuridique=" + codeFormeJuridique + " ]";
    }
    
}
