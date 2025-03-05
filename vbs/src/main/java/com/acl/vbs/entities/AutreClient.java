/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DEV
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTRE_CLIENT")
public class AutreClient implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
    @Column(name = "CODE_AUTRE_CLIENT")
    private String codeAutreClient;
    @Basic(optional = false)
    @Column(name = "DEPOTAGE_PORT")
    private short depotagePort;    
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;
    @JoinColumn(name = "CODE_TYPE_AUTRE_CLIENT", referencedColumnName = "CODE_TYPE_AUTRE_CLIENT")
    @ManyToOne(optional = false)
    private TypeAutreClient typeAutreClient;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutreClient)) {
            return false;
        }
        AutreClient other = (AutreClient) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.AutreClient[ idClient=" + idClient + " ]";
    }
    
}
