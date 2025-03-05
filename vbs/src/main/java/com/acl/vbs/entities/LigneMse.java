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
import java.util.Date;

/**
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "LIGNE_MSE")
public class LigneMse extends Object implements Serializable, Comparable<LigneMse> {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_LIGNE_MSE")
    @Basic(optional = false)
    @Column(name = "ID_LIGNE_MSE")
    private Long idLigneMse;

    @Column(name = "DESC_MSE")
    private String descMse;

    @Basic(optional = false)
    @Column(name = "POIDS_BRUT")
    private BigDecimal poidsBrut;

    @Column(name = "PLOMB")
    private String plomb;

    @Column(name = "DATE_VAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVaq;

    @Column(name = "DATE_VAS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVas;

    @Column(name = "DATE_VSP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVsp;

    @Column(name = "DATE_VEQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVeq;

    @Column(name = "DATE_VES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVes;

    @Basic(optional = false)
    @Column(name = "DEPOTEE")
    private Boolean depotee;

    @Column(name = "PLOMB2")
    private String plomb2;

    @Column(name = "PLOMB3")
    private String plomb3;

    @Column(name = "DATE_VAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVab;

    @Column(name = "DATE_VEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVep;

    @Basic(optional = false)
    @Column(name = "TOTALEMENT_SORTIE")
    private Boolean totalementSortie;

    @Basic(optional = false)
    @Column(name = "TRANSFEREE")
    private Boolean transferee;

    @Basic(optional = false)
    @Column(name = "POIDS_DECL")
    private BigDecimal poidsDecl;

    @Column(name = "POIDS_NET")
    private BigDecimal poidsNet;

    @JoinColumn(name = "PAYS_ORIGINE", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays paysOrigine;

    @JsonIgnore
    @JoinColumn(name = "CODE_MARCHANDISE", referencedColumnName = "CODE_MARCHANDISE")
    @ManyToOne(optional = false)
    private Marchandise codeMarchandise;

    @JsonIgnore
    @JoinColumn(name = "MANUTENTIONNAIRE", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Concessionnaire manutentionnaire;

    @JsonIgnore
    @JoinColumn(name = "NUM_CONTENEUR", referencedColumnName = "NUM_CONTENEUR")
    @ManyToOne
    private Conteneur numConteneur;

    @JsonIgnore
    @JoinColumn(name = "ID_BL", referencedColumnName = "ID_BL")
    @ManyToOne(optional = false)
    private Bl idBl;

    public int hashCode() {
        int hash = 0;
        return (this.idLigneMse != null) ? this.idLigneMse.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof LigneMse)) {
            return false;
        }
        LigneMse other = (LigneMse) object;
        if ((this.idLigneMse == null && other.idLigneMse != null) || (this.idLigneMse != null && !this.idLigneMse.equals(other.idLigneMse))) {
            return false;
        }
        return true;
    }

    //public String toString() { return "LigneMse[ code mse = " + this.codeMarchandise + ", num conteneur = " + this.numConteneur + " ]"; }
    public String toString() {
        return "LigneMse[ idLigneMse = " + this.idLigneMse + " ]";
    }


    public int compareTo(LigneMse o) {
        if (o == null || o.getIdLigneMse() == null)
            return 1;
        if (getIdLigneMse() == null) {
            return 0;
        }
        return (getIdLigneMse() != null && o.getIdLigneMse() != null) ? getIdLigneMse().compareTo(o.getIdLigneMse()) : 0;
    }

}
