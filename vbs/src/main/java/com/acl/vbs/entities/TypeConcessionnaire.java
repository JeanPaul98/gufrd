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
@Table(name = "TYPE_CONCESSIONNAIRE")
public class TypeConcessionnaire implements Serializable {
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_TYPE_CONC")
    private String codeTypeConc;
    @Basic(optional = false)
    @Column(name = "LIB_TYPE_CONC")
    private String libTypeConc;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTypeConc != null ? codeTypeConc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeConcessionnaire)) {
            return false;
        }
        TypeConcessionnaire other = (TypeConcessionnaire) object;
        if ((this.codeTypeConc == null && other.codeTypeConc != null) || (this.codeTypeConc != null && !this.codeTypeConc.equals(other.codeTypeConc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.TypeConcessionnaire[ codeTypeConc=" + codeTypeConc + " ]";
    }
    
}
