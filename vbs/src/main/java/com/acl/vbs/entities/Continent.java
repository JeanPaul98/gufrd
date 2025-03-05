/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 11/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTINENT")
public class Continent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_CONTINENT")
    private String codeContinent;
    @Column(name = "LIB_CONTINENT")
    private String libContinent;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeContinent != null ? codeContinent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Continent)) {
            return false;
        }
        Continent other = (Continent) object;
        if ((this.codeContinent == null && other.codeContinent != null) || (this.codeContinent != null && !this.codeContinent.equals(other.codeContinent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Continent[ codeContinent=" + codeContinent + " ]";
    }
    
    
}
