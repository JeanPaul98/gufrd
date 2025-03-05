/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Elikplim 18/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "MARCHANDISE")
public class Marchandise implements Serializable, Comparable<Marchandise> {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_MARCHANDISE")
    private String codeMarchandise;
    @Basic(optional = false)
    @Column(name = "LIB_MARCHANDISE")
    private String libMarchandise;
    @Column(name = "MOTS_CLES")
    private String motsCles;
    @Column(name = "DESC_MSE")
    private String descMse;
    @Column(name = "NUMERO_UN")
    private String numeroUn;
    @Column(name = "QTE_LIMITE")
    private BigDecimal qteLimite;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "codeMarchandise")
    private List<LigneMse> ligneMseList;
    @JsonIgnore
    @JoinColumn(name = "CODE_SOUS_CATEG_MSE", referencedColumnName = "CODE_SOUS_CATEG_MSE")
    @ManyToOne(optional = false)
    private SousCategMse codeSousCategMse;

    public int hashCode() {
        int hash = 0;
        return (this.codeMarchandise != null) ? this.codeMarchandise.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Marchandise)) {
            return false;
        }
        Marchandise other = (Marchandise) object;
        if ((this.codeMarchandise == null && other.codeMarchandise != null) || (this.codeMarchandise != null && !this.codeMarchandise.equals(other.codeMarchandise))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getClass().getSimpleName() + " :: ID = " + this.codeMarchandise + " :: LIB = " + this.libMarchandise;
    }


    public int compareTo(Marchandise o) {
        if (o == null || o.getLibMarchandise() == null)
            return 1;
        if (getLibMarchandise() == null) {
            return 0;
        }
        return (getLibMarchandise() != null && o.getLibMarchandise() != null) ? getLibMarchandise().compareTo(o.getLibMarchandise()) : 0;
    }
}
