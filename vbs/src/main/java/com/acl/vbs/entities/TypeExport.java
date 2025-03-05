/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TYPE_EXPORT")
public class TypeExport implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_TYPE_EXPORT")
    private String codeTypeExport;
    @Basic(optional = false)
    @Column(name = "LIB_TYPE_EXPORT")
    private String libTypeExport;
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTypeExport != null ? codeTypeExport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeExport)) {
            return false;
        }
        TypeExport other = (TypeExport) object;
        if ((this.codeTypeExport == null && other.codeTypeExport != null) || (this.codeTypeExport != null && !this.codeTypeExport.equals(other.codeTypeExport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.TypeExport[ codeTypeExport=" + codeTypeExport + " ]";
    }
    
    
}
