/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "TYPE_TRANSPORTEUR")
public class TypeTransporteur extends Object implements Serializable, Comparable<TypeTransporteur>{
       private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_TYPE_TRANS")
    private String codeTypeTrans;
    @Basic(optional = false)
    @Column(name = "LIB_TYPE_TRANS")
    private String libTypeTrans;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "codeTypeTrans")
    private List<Transporteur> transporteurList; 

    public int hashCode() {
        int hash = 0;
        return (this.codeTypeTrans != null) ? this.codeTypeTrans.hashCode() : 0;
    }
 
    public boolean equals(Object object) {
        if (!(object instanceof TypeTransporteur)) {
        return false;
    }
    
    TypeTransporteur other = (TypeTransporteur)object;
    
    if ((this.codeTypeTrans == null && other.codeTypeTrans != null) || (this.codeTypeTrans != null && !this.codeTypeTrans.equals(other.codeTypeTrans))) {
       return false;
     }
     return true;
   }

   public String toString() { return getClass().getSimpleName() + " :: ID = " + this.codeTypeTrans + " :: LIB = " + this.libTypeTrans; }
     public int compareTo(TypeTransporteur o) {
     if (o == null || o.getLibTypeTrans() == null)
      return 1; 
     if (getLibTypeTrans() == null) {
      return 0;
   }
    return (getLibTypeTrans() != null && o.getLibTypeTrans() != null) ? getLibTypeTrans().compareTo(o.getLibTypeTrans()) : 0;
   }
}
