/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 15/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ARMATEUR")
public class Armateur {
     private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ARMATEUR")
    private Integer idArmateur;
    @Basic(optional = false)
    @Column(name = "RAISON_SOCIALE_ARMATEUR")
    private String raisonSocialeArmateur;
    @Column(name = "ADRESSE_ARMATEUR")
    private String adresseArmateur;
    @Column(name = "TEL_ARMATEUR")
    private String telArmateur;
    @Column(name = "BP_ARMATEUR")
    private String bpArmateur;
    @Column(name = "EMAIL_ARMATEUR")
    private String emailArmateur;
    @Column(name = "VILLE_ARMATEUR")
    private String villeArmateur;
    @Column(name = "FAX_ARMATEUR")
    private String faxArmateur;
    @Column(name = "PERS_CONTACT_ARMATEUR")
    private String persContactArmateur;
    @Column(name = "TEL_PERS_CONTACT_ARMATEUR")
    private String telPersContactArmateur;
    @Column(name = "CODE_ARMATEUR")
    private String codeArmateur;
    @JoinColumn(name = "SIEGE_SOCIAL", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays pays;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Armateur)) {
            return false;
        }
        Armateur other = (Armateur) object;
        if ((this.idArmateur == null && other.idArmateur != null) || (this.idArmateur != null && !this.idArmateur.equals(other.idArmateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Armateur[ idArmateur=" + idArmateur + " ]";
    }
}
