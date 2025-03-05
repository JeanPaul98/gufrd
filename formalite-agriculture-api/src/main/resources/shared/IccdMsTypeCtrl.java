/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_MS_TYPE_CTRL", catalog = "", schema = "SIPEDBA")
public class IccdMsTypeCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "ID_TYPE_CTRL")
    private Long idMsTypeControle;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;
    @Column(name = "MONTANT")
    private Double montant;
    

    public IccdMsTypeCtrl() {
    }

    public IccdMsTypeCtrl(Long idMsTypeControle) {
        this.idMsTypeControle = idMsTypeControle;
    }

    public Long getIdMsTypeControle() {
        return idMsTypeControle;
    }

    public void setIdMsTypeControle(Long idMsTypeControle) {
        this.idMsTypeControle = idMsTypeControle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsTypeControle != null ? idMsTypeControle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsTypeCtrl)) {
            return false;
        }
        IccdMsTypeCtrl other = (IccdMsTypeCtrl) object;
        if ((this.idMsTypeControle == null && other.idMsTypeControle != null) || (this.idMsTypeControle != null && !this.idMsTypeControle.equals(other.idMsTypeControle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsTypeCtrl[ idMsTypeControle=" + idMsTypeControle + " ]";
    }
    
}
