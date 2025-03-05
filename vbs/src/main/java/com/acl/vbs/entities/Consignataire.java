/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Elikplim 13/11/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONSIGNATAIRE")
public class Consignataire implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
    @Column(name = "CODE_CONSIGNATAIRE")
    private String codeConsignataire;   
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consignataire)) {
            return false;
        }
        Consignataire other = (Consignataire) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.Consignataire[ idClient=" + idClient + " ]";
    }
}
