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
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "STATUT_BL")
public class StatutBl implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_STATUT_BL")
    private String codeStatutBl;
    @Basic(optional = false)
    @Column(name = "LIB_STATUT_BL")
    private String libStatutBl;
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeStatutBl != null ? codeStatutBl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatutBl)) {
            return false;
        }
        StatutBl other = (StatutBl) object;
        if ((this.codeStatutBl == null && other.codeStatutBl != null) || (this.codeStatutBl != null && !this.codeStatutBl.equals(other.codeStatutBl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.StatutBl[ codeStatutBl=" + codeStatutBl + " ]";
    }
}
