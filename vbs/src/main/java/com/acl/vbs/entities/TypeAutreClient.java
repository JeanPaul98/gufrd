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
@Table(name = "TYPE_AUTRE_CLIENT")
public class TypeAutreClient implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_TYPE_AUTRE_CLIENT")
    private String codeTypeAutreClient;
    @Basic(optional = false)
    @Column(name = "LIB_TYPE_AUTRE_CLIENT")
    private String libTypeAutreClient;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTypeAutreClient != null ? codeTypeAutreClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeAutreClient)) {
            return false;
        }
        TypeAutreClient other = (TypeAutreClient) object;
        if ((this.codeTypeAutreClient == null && other.codeTypeAutreClient != null) || (this.codeTypeAutreClient != null && !this.codeTypeAutreClient.equals(other.codeTypeAutreClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.TypeAutreClient[ codeTypeAutreClient=" + codeTypeAutreClient + " ]";
    }
    
    
    
}
