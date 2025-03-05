/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

//import jakarta.persistence.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BecLigneMsePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUM_BON_ENTREE_CAMION")
    private String numBonEntreeCamion;
    @Basic(optional = false)
    @Column(name = "ID_LIGNE_MSE")
    private long idLigneMse;

    public String getNumBonEntreeCamion() {
        return numBonEntreeCamion;
    }

    public void setNumBonEntreeCamion(String numBonEntreeCamion) {
        this.numBonEntreeCamion = numBonEntreeCamion;
    }

    public long getIdLigneMse() {
        return idLigneMse;
    }

    public void setIdLigneMse(long idLigneMse) {
        this.idLigneMse = idLigneMse;
    }


    public int hashCode() {
        int hash = 0;
        hash += ((this.numBonEntreeCamion != null) ? this.numBonEntreeCamion.hashCode() : 0);
        return (int) this.idLigneMse;
    }

    public boolean equals(Object object) {
        if (!(object instanceof BecLigneMsePK)) {
            return false;
        }

        BecLigneMsePK other = (BecLigneMsePK) object;
        if ((this.numBonEntreeCamion == null && other.numBonEntreeCamion != null) || (this.numBonEntreeCamion != null && !this.numBonEntreeCamion.equals(other.numBonEntreeCamion))) {
            return false;
        }

        if (this.idLigneMse != other.idLigneMse) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "com.acl.sipe.entities.BecLigneMsePK[ numBonEntreeCamion=" + this.numBonEntreeCamion + ", idLigneMse=" + this.idLigneMse + " ]";
    }

}
