/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Elikplim 11/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSPORTEUR")
public class Transporteur implements Serializable, Comparable<Transporteur> {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_TRANSPORTEUR")
    private String codeTransporteur;
    @Basic(optional = false)
    @Column(name = "LIB_TRANSPORTEUR")
    private String libTransporteur;
    @JsonIgnore
    @JoinColumn(name = "CODE_TYPE_TRANS", referencedColumnName = "CODE_TYPE_TRANS")
    @ManyToOne(optional = false)
    private TypeTransporteur codeTypeTrans;

    public int hashCode() {
        int hash = 0;
        return (this.codeTransporteur != null) ? this.codeTransporteur.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Transporteur)) {
            return false;
        }
        Transporteur other = (Transporteur) object;
        if ((this.codeTransporteur == null && other.codeTransporteur != null) || (this.codeTransporteur != null && !this.codeTransporteur.equals(other.codeTransporteur))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return getClass().getSimpleName() + " :: ID = " + this.codeTransporteur + " :: LIB = " + this.libTransporteur;
    }


    public int compareTo(Transporteur o) {
        if (o == null || o.getLibTransporteur() == null)
            return 1;
        if (getLibTransporteur() == null) {
            return 0;
        }
        return getLibTransporteur().compareTo(o.getLibTransporteur());
    }


}
