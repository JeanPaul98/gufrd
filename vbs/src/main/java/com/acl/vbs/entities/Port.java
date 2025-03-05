/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim15/11/2024
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "PORT")
public class Port {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_PORT")
    private String codePort;
    @Column(name = "LOCODE")
    private String locode;
    @Basic(optional = false)
    @Column(name = "NOM_PORT")
    private String nomPort;
    @Column(name = "TIME_ZONE_PORT")
    private String timeZonePort;
    @Column(name = "VILLE_PORT")
    private String villePort;
    @Column(name = "CODE_VILLE_PORT")
    private String codeVillePort;
    @Column(name = "ADRESSE_PORT")
    private String adressePort;
    @Column(name = "TEL_PORT")
    private String telPort;
    @Column(name = "FAX_PORT")
    private String faxPort;
    @Column(name = "EMAIL_PORT")
    private String emailPort;
    @Column(name = "SITE_WEB_PORT")
    private String siteWebPort;
    @Basic(optional = false)
    @Column(name = "LISTE_NOIRE")
    private short listeNoire;
    @Column(name = "DATE_AJOUT_LISTE_NOIRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjoutListeNoire;
    @Column(name = "DATE_RETRAIT_LISTE_NOIRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetraitListeNoire;    
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays pays;
    
        @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePort != null ? codePort.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Port)) {
            return false;
        }
        Port other = (Port) object;
        if ((this.codePort == null && other.codePort != null) || (this.codePort != null && !this.codePort.equals(other.codePort))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Port[ codePort=" + codePort + " ]";
    }
    
}
