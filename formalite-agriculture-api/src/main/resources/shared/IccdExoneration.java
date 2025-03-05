/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_DMD_EXONERATION")
@SequenceGenerator(name = "S_ICCD_EXONERATION",sequenceName = "S_ICCD_EXONERATION", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IccdExoneration.findAll", query = "SELECT i FROM IccdExoneration i"),
    @NamedQuery(name = "IccdExoneration.findByIdExoneration", query = "SELECT i FROM IccdExoneration i WHERE i.idExoneration = :idExoneration"),
    @NamedQuery(name = "IccdExoneration.findByDateDebut", query = "SELECT i FROM IccdExoneration i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IccdExoneration.findByDateFin", query = "SELECT i FROM IccdExoneration i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IccdExoneration.findByDateCreation", query = "SELECT i FROM IccdExoneration i WHERE i.dateCreation = :dateCreation"),
    @NamedQuery(name = "IccdExoneration.findByEtat", query = "SELECT i FROM IccdExoneration i WHERE i.etat = :etat"),
    @NamedQuery(name = "IccdExoneration.findByCompteClient", query = "SELECT i FROM IccdExoneration i WHERE i.compteClient = :compteClient")})
public class IccdExoneration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_EXONERATION")
    @Column(name = "ID_EXONERATION")
    private Long idExoneration;
    
    @Column(name = "DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "ETAT")
    private Short etat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "COMPTE_CLIENT")
    private String compteClient;
    
    @Size(min = 1, max = 255)
    @Column(name = "ACTIVITE")
    private String activite;  
    
    @Size(min = 1, max = 200)
    @Column(name = "TYPE")
    private String type;    
    
    @Size(min = 1, max = 200)
    @Column(name = "CATEGORIE")
    private String categorie;
    
    @Size(min = 1, max = 200)
    @Column(name = "MOTIF")
    private String motif;
    
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "RAISON_SOCIALE_CLIENT")
    private String raisonSocialeClient;

    public IccdExoneration() {
    }

    public IccdExoneration(Long idExoneration) {
        this.idExoneration = idExoneration;
    }

    public IccdExoneration(Long idExoneration, String compteClient) {
        this.idExoneration = idExoneration;
        this.compteClient = compteClient;
    }

    public Long getIdExoneration() {
        return idExoneration;
    }

    public void setIdExoneration(Long idExoneration) {
        this.idExoneration = idExoneration;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Short getEtat() {
        return etat;
    }

    public void setEtat(Short etat) {
        this.etat = etat;
    }

    public String getCompteClient() {
        return compteClient;
    }

    public void setCompteClient(String compteClient) {
        this.compteClient = compteClient;
    }

    /**
     * @return the type exoneration (Exoneration TVA, Exonération Frais portuaire, Reduction)
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the activite
     */
    public String getActivite() {
        return activite;
    }

    /**
     * @param activite the activite to set
     */
    public void setActivite(String activite) {
        this.activite = activite;
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * @return the raisonSocialeClient
     */
    public String getRaisonSocialeClient() {
        return raisonSocialeClient;
    }

    /**
     * @param raisonSocialeClient the raisonSocialeClient to set
     */
    public void setRaisonSocialeClient(String raisonSocialeClient) {
        this.raisonSocialeClient = raisonSocialeClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExoneration != null ? idExoneration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdExoneration)) {
            return false;
        }
        IccdExoneration other = (IccdExoneration) object;
        if ((this.idExoneration == null && other.idExoneration != null) || (this.idExoneration != null && !this.idExoneration.equals(other.idExoneration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdExoneration[ idExoneration=" + idExoneration + " ]";
    }
    
}
