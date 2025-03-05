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
 * @author Elikplim 18/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "SENS_TRAFIC")
public class SensTrafic implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_SENS_TRAFIC")
    private String codeSensTrafic;
    @Basic(optional = false)
    @Column(name = "LIB_SENS_TRAFIC")
    private String libSensTrafic;
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeSensTrafic != null ? codeSensTrafic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensTrafic)) {
            return false;
        }
        SensTrafic other = (SensTrafic) object;
        if ((this.codeSensTrafic == null && other.codeSensTrafic != null) || (this.codeSensTrafic != null && !this.codeSensTrafic.equals(other.codeSensTrafic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.SensTrafic[ codeSensTrafic=" + codeSensTrafic + " ]";
    }
    
    
}
