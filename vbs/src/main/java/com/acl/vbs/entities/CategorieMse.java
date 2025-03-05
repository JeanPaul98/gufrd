/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 18//11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "CATEGORIE_MSE")
public class CategorieMse implements Serializable, Comparable<CategorieMse>{
       private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "CODE_CATEGORIE_MSE")
   private String codeCategorieMse;
   @Basic(optional = false)
   @Column(name = "LIB_CATEGORIE_MSE")
   private String libCategorieMse;
   
   @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "codeCategorieMse")
   private List<SousCategMse> sousCategMseList;
    
      public int hashCode() {
     int hash = 0;
     return (this.codeCategorieMse != null) ? this.codeCategorieMse.hashCode() : 0;
   }
 
 
   
   public boolean equals(Object object) {
     if (!(object instanceof CategorieMse)) {
       return false;
     }
     CategorieMse other = (CategorieMse)object;
     if ((this.codeCategorieMse == null && other.codeCategorieMse != null) || (this.codeCategorieMse != null && !this.codeCategorieMse.equals(other.codeCategorieMse))) {
       return false;
     }
     return true;
   }
 
   
   public String toString() { return getClass().getSimpleName() + " :: ID = " + this.codeCategorieMse + " :: LIB = " + this.libCategorieMse; }
 
  
   public int compareTo(CategorieMse o) {
     if (o == null || o.getLibCategorieMse() == null)
       return 1; 
     if (getLibCategorieMse() == null) {
       return 0;
     }
     return (getLibCategorieMse() != null && o.getLibCategorieMse() != null) ? getLibCategorieMse().compareTo(o.getLibCategorieMse()) : 0;
   }
}
