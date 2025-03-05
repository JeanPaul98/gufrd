/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.vbs.entities;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
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
 * @author Elikplim 13/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONCESSIONNAIRE")
public class Concessionnaire implements Serializable{
        @Serial
        private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
    @Column(name = "CODE_CONCESSIONNAIRE")
    private String codeConcessionnaire;
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Client client;
    @JsonIgnore
    @JoinColumn(name = "CODE_TYPE_CONC", referencedColumnName = "CODE_TYPE_CONC")
    @ManyToOne(optional = false)
    private TypeConcessionnaire typeConcessionnaire;
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concessionnaire)) {
            return false;
        }
        Concessionnaire other = (Concessionnaire) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Concessionnaire{" +
                "idClient=" + idClient +
                ", codeConcessionnaire='" + codeConcessionnaire + '\'' +
                '}';
    }
}
