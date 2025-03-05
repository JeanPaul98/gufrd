/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 18/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "SOUS_CATEG_MSE")
public class SousCategMse implements Serializable, Comparable<SousCategMse>{
  private static final long serialVersionUID = 1L;
   @Id
   @Basic(optional = false)
   @Column(name = "CODE_SOUS_CATEG_MSE")
   private String codeSousCategMse;
   @Basic(optional = false)
   @Column(name = "LIB_SOUS_CATEG_MSE")
   private String libSousCategMse;
   @Basic(optional = false)
   @Column(name = "ACTIF")
   private Boolean actif;
   @Basic(optional = false)
   @Column(name = "MNT_REMISE")
   private BigDecimal mntRemise;
   @Basic(optional = false)
   @Column(name = "TAUX_REMISE")
   private BigDecimal tauxRemise;
   @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "codeSousCategMse")
   private List<Marchandise> marchandiseList;
   @JoinColumn(name = "CODE_CATEGORIE_MSE", referencedColumnName = "CODE_CATEGORIE_MSE")
   @ManyToOne(optional = false)
   private CategorieMse codeCategorieMse;  
   
      public int hashCode() {
    int hash = 0;
     return (this.codeSousCategMse != null) ? this.codeSousCategMse.hashCode() : 0;
   }
   
   public boolean equals(Object object) {
     if (!(object instanceof SousCategMse)) {
       return false;
     }
     SousCategMse other = (SousCategMse)object;
     if ((this.codeSousCategMse == null && other.codeSousCategMse != null) || (this.codeSousCategMse != null && !this.codeSousCategMse.equals(other.codeSousCategMse))) {
       return false;
     }
     return true;
   }
 
 
   
   public String toString() { return getClass().getSimpleName() + " :: ID = " + this.codeSousCategMse + " :: LIB = " + this.libSousCategMse + ((getCodeCategorieMse() != null) ? (" :: CATEGORIE = " + getCodeCategorieMse().getLibCategorieMse()) : ""); }
   
   public int compareTo(SousCategMse o) {
     if (o == null || o.getLibSousCategMse() == null)
       return 1; 
     if (getLibSousCategMse() == null) {
       return 0;
     }
     return (getLibSousCategMse() != null && o.getLibSousCategMse() != null) ? getLibSousCategMse().compareTo(o.getLibSousCategMse()) : 0;
   }
}
