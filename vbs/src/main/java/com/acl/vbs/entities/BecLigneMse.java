/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
public class BecLigneMse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BecLigneMsePK becLigneMsePK;
    @Column(name = "DATE_SORTIE_MSE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortieMse;
    @JoinColumn(name = "ID_LIGNE_MSE", referencedColumnName = "ID_LIGNE_MSE", insertable = false, updatable = false)
    @ManyToOne
    private LigneMse ligneMse;
    @JoinColumn(name = "NUM_BON_ENTREE_CAMION", referencedColumnName = "NUM_BON_ENTREE_CAMION", insertable = false, updatable = false)
    @ManyToOne
    private BonEntreeCamion bonEntreeCamion;

    public int hashCode() {
        int hash = 0;
        return (this.becLigneMsePK != null) ? this.becLigneMsePK.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof BecLigneMse)) {
            return false;
        }
        BecLigneMse other = (BecLigneMse) object;
        if ((this.becLigneMsePK == null && other.becLigneMsePK != null) || (this.becLigneMsePK != null && !this.becLigneMsePK.equals(other.becLigneMsePK))) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "com.acl.sipe.entities.BecLigneMse[ NUM_BON_ENTREE_CAMION=" + this.bonEntreeCamion + " ID_LIGNE_MSE=" + this.ligneMse + "]";
    }
}
