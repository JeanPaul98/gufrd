/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 18/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "TYPE_CONTENEUR")
public class TypeConteneur implements Serializable{
    
    private static final long serialVersionUID = 1L;   
    @Id
    @Column(name="CODE_TYPE_CONTENEUR")
    private String code;
    @Column(name="LIB_TYPE_CONTENEUR")
    private String libelle;
    @Column(name="CODE_CATEG_CONTENEUR")
    private String categConteneur;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeConteneur)) {
            return false;
        }
        TypeConteneur other = (TypeConteneur) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.TypeConteneur[ code=" + code + " ]";
    }

    
}
