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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_MS_RENCERTIF", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_RENCERTIF",sequenceName = "S_ICCD_MS_RENCERTIF", allocationSize = 1)
public class IccdMsRenCertif implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_RENCERTIF")
    @Column(name = "ID_RENOUVEL_CERTIF")
    private Long idRenouvelCertif;
    
    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;
    @Size(max = 254)
    @Column(name = "NUMERO_CERTIFICAT")
    private String numeroCertificat;
    @Column(name = "DATE_DMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDmd;
    @Size(max = 254)
    @Column(name = "REMARQUES")
    private String remarques;
    @Column(name = "MONTANT")
    private Double montant;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formaliteMinisterielle;
    

    public IccdMsRenCertif() {
    }

    public IccdMsRenCertif(Long idRenouvelCertif) {
        this.idRenouvelCertif = idRenouvelCertif;
    }

    public Long getIdRenouvelCertifMs() {
        return idRenouvelCertif;
    }

    public void setIdRenouvelCertifMs(Long idRenouvelCertif) {
        this.idRenouvelCertif = idRenouvelCertif;
    }

    public String getNumeroCertificat() {
        return numeroCertificat;
    }

    public void setNumeroCertificat(String numeroCertificat) {
        this.numeroCertificat = numeroCertificat;
    }

    public Date getDateDmd() {
        return dateDmd;
    }

    public void setDateDmd(Date dateDmd) {
        this.dateDmd = dateDmd;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Long getIdRenouvelCertif() {
        return idRenouvelCertif;
    }

    public void setIdRenouvelCertif(Long idRenouvelCertif) {
        this.idRenouvelCertif = idRenouvelCertif;
    }

    public String getNumGenere() {
        return numGenere;
    }

    public void setNumGenere(String numGenere) {
        this.numGenere = numGenere;
    }

    public IccdFormaliteMinisterielle getFormaliteMinisterielle() {
        return formaliteMinisterielle;
    }

    public void setFormaliteMinisterielle(IccdFormaliteMinisterielle formaliteMinisterielle) {
        this.formaliteMinisterielle = formaliteMinisterielle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRenouvelCertif != null ? idRenouvelCertif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsRenCertif)) {
            return false;
        }
        IccdMsRenCertif other = (IccdMsRenCertif) object;
        if ((this.idRenouvelCertif == null && other.idRenouvelCertif != null) || (this.idRenouvelCertif != null && !this.idRenouvelCertif.equals(other.idRenouvelCertif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsRenCertif[ idRenouvelCertif=" + idRenouvelCertif + " ]";
    }
    
}
