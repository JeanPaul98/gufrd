/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DEV
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SITE", schema = "SIPEDBA")
@Entity
public class Site implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_seq")
    @Basic(optional = false)
    @Column(name = "ID_SITE")
    private Long idSite;
    @Basic(optional = false)
    @Column(name = "NOM_SITE")
    private String nomSite;
    @Basic(optional = false)
    @Column(name = "REDEVANCE")
    private Long redevance;
    @JoinColumn(name="CONCESSIONNAIRE", referencedColumnName = "ID_CLIENT")
    @JsonIgnore
    @ManyToOne
    private Client client;

        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSite != null ? idSite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        return !((this.idSite == null && other.idSite != null) || (this.idSite != null && !this.idSite.equals(other.idSite)));
    }

    @Override
    public String toString() {
        return "com.acl.sipe.entities.Site[ idSite=" + idSite + " ]";
    }
    
    
}
