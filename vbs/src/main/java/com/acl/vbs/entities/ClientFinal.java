/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Table(name = "CLIENT_FINAL")
public class ClientFinal implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT_FINAL")
    private Integer idClientFinal;
    @Basic(optional = false)
    @Column(name = "NOM_CLIENT_FINAL")
    private String nomClientFinal;
    @Column(name = "ADRESSE_CLIENT_FINAL")
    private String adresseClientFinal;    
    @JoinColumn(name = "NATIONALITE", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays pays;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClientFinal != null ? idClientFinal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientFinal)) {
            return false;
        }
        ClientFinal other = (ClientFinal) object;
        if ((this.idClientFinal == null && other.idClientFinal != null) || (this.idClientFinal != null && !this.idClientFinal.equals(other.idClientFinal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.ClientFinal[ idClientFinal=" + idClientFinal + " ]";
    }
}
