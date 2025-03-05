/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "MOTIF_CHANGE_DEST")
public class MotifChangeDest {
   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_MOTIF_CHANGE_DEST")
    private String codeMotifChangeDest;
    @Basic(optional = false)
    @Column(name = "LIB_MOTIF_CHANGE_DEST")
    private String libMotifChangeDest;
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotifChangeDest)) {
            return false;
        }
        MotifChangeDest other = (MotifChangeDest) object;
        if ((this.codeMotifChangeDest == null && other.codeMotifChangeDest != null) || (this.codeMotifChangeDest != null && !this.codeMotifChangeDest.equals(other.codeMotifChangeDest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.MotifChangeDest[ codeMotifChangeDest=" + codeMotifChangeDest + " ]";
    }
}
