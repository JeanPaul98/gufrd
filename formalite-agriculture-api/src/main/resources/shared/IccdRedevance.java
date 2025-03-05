/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias
 */

@Entity
@Table(name = "ICCD_REDEVANCE", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_REDEVANCE",sequenceName = "S_ICCD_REDEVANCE", allocationSize = 1)
public class IccdRedevance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_REDEVANCE")
    @Column(name = "ID_REDEVANCE")
    private Long idRedevance;
    @Size(max = 254)
    @Column(name = "DEMANDE")
    private String demande;
    
    @Column(name = "MONTANT")
    private Double montant;
    
    @Column(name = "STATUS")
    private short status;
    
    @Column(name = "DATE_DEMANDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdFormaliteMinisterielle formalite;

    public IccdRedevance() {
    }    
    
    public IccdRedevance(Long idRedevance) {
        this.idRedevance = idRedevance;
    }

    public Long getIdRedevance() {
        return idRedevance;
    }

    public void setIdRedevance(Long idRedevance) {
        this.idRedevance = idRedevance;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public com.acl.iccd.core.entities.IccdFormaliteMinisterielle getFormalite() {
        return formalite;
    }

    public void setFormalite(IccdFormaliteMinisterielle formalite) {
        this.formalite = formalite;
    }
    
}