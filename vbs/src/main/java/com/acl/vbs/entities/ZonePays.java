/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;

import jakarta.persistence.*;
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
@Table(name = "ZONE_PAYS")
public class ZonePays implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_ZONE_PAYS")
    private String codeZonePays;
    @Basic(optional = false)
    @Column(name = "LIB_ZONE_PAYS")
    private String libZonePays;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeZonePays != null ? codeZonePays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZonePays)) {
            return false;
        }
        ZonePays other = (ZonePays) object;
        if ((this.codeZonePays == null && other.codeZonePays != null) || (this.codeZonePays != null && !this.codeZonePays.equals(other.codeZonePays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.ZonePays[ codeZonePays=" + codeZonePays + " ]";
    }
    
}
