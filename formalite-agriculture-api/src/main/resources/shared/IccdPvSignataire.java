/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_PV_SIGNATAIRE")
@SequenceGenerator(name = "S_ICCD_PV_SIGNATAIRE",sequenceName = "S_ICCD_PV_SIGNATAIRE", allocationSize = 1)
@XmlRootElement
public class IccdPvSignataire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_PV_SIGNATAIRE")
    @Column(name = "ID_PV")
    private Long idPv;
    
    @Column(name = "ID_SIGNATAIRE")
    private int idSignataire;
    
    @Size(max = 254)
    @Column(name = "NOM")
    private String nom;
    
    @Size(max = 254)
    @Column(name = "PRENOMS")
    private String prenoms;
    
    @Column(name = "DATE_SIGNATURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSignature;

    public IccdPvSignataire() {
    }

    public IccdPvSignataire(Long idPv) {
        this.idPv = idPv;
    }

    public Long getIdPv() {
        return idPv;
    }

    public void setIdPv(Long idPv) {
        this.idPv = idPv;
    }

    public int getIdSignataire() {
        return idSignataire;
    }

    public void setIdSignataire(int idSignataire) {
        this.idSignataire = idSignataire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPv != null ? idPv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdPvSignataire)) {
            return false;
        }
        IccdPvSignataire other = (IccdPvSignataire) object;
        if ((this.idPv == null && other.idPv != null) || (this.idPv != null && !this.idPv.equals(other.idPv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdPvSignataire[ idPv=" + idPv + " ]";
    }
    
}
