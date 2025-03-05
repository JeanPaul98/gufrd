/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.IccdMsRenCertif;
import com.acl.iccd.core.entities.IccdStructure;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_HISTORIQUE_FORMALITE", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_HISTORIQUE_FORMALITE",sequenceName = "S_ICCD_HISTORIQUE_FORMALITE", allocationSize = 1)
public class IccdHistoriqueFormalite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_HISTORIQUE_FORMALITE")
    @Column(name = "ID_HISTORIQUE")
    private Long idHistorique;
    @Size(max = 254)
    @Column(name = "TYPE_DMD")
    private String typeDmd;    
    @Column(name = "ETAT")
    private short etat;
    @Column(name = "DATE_DMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDmd;
    @Column(name = "DATE_VALIDATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidation;
    @Size(max = 254)
    @Column(name = "DECISSION")
    private String decission;
    @Size(max = 254)
    @Column(name = "OBSERVATIONS")
    private String observations;
    @Column(name = "DATE_ANNULATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;
    @Size(max = 254)
    @Column(name = "MOTIF_ANNULATION")
    private String motifAnnulation;
    @Column(name = "CHAINE")
    private String chaine;
    @Column(name = "COMPTE_CLIENT")
    private String compteClient; 
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne(optional = false)
    private IccdFormaliteMinisterielle idFm;
    @JoinColumn(name = "ID_RENOUVEL_CERTIF", referencedColumnName = "ID_RENOUVEL_CERTIF")
    @ManyToOne
    private IccdMsRenCertif iccdRenouvelCertifMs;
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    @ManyToOne(optional = false)
    private IccdStructure iccdStructure;
    

    public IccdHistoriqueFormalite() {
    }

    
    public Long getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(Long idHistorique) {
        this.idHistorique = idHistorique;
    }

    
    public String getDecission() {
        return decission;
    }

    public void setDecission(String decission) {
        this.decission = decission;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    /**
     * @return the typeDmd
     */
    public String getTypeDmd() {
        return typeDmd;
    }

    /**
     * @param typeDmd the typeDmd to set
     */
    public void setTypeDmd(String typeDmd) {
        this.typeDmd = typeDmd;
    }

    /**
     * @return the etat
     */
    public short getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(short etat) {
        this.etat = etat;
    }

    /**
     * @return the dateDmd
     */
    public Date getDateDmd() {
        return dateDmd;
    }

    /**
     * @param dateDmd the dateDmd to set
     */
    public void setDateDmd(Date dateDmd) {
        this.dateDmd = dateDmd;
    }

    /**
     * @return the dateValidation
     */
    public Date getDateValidation() {
        return dateValidation;
    }

    /**
     * @param dateValidation the dateValidation to set
     */
    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    /**
     * @return the dateAnnulation
     */
    public Date getDateAnnulation() {
        return dateAnnulation;
    }

    /**
     * @param dateAnnulation the dateAnnulation to set
     */
    public void setDateAnnulation(Date dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    /**
     * @return the motifAnnulation
     */
    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    /**
     * @param motifAnnulation the motifAnnulation to set
     */
    public void setMotifAnnulation(String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    /**
     * @return the chaine
     */
    public String getChaine() {
        return chaine;
    }

    /**
     * @param chaine the chaine to set
     */
    public void setChaine(String chaine) {
        this.chaine = chaine;
    }

    /**
     * @return the compteClient
     */
    public String getCompteClient() {
        return compteClient;
    }

    /**
     * @param compteClient the compteClient to set
     */
    public void setCompteClient(String compteClient) {
        this.compteClient = compteClient;
    }

    /**
     * @return the iccdRenouvelCertifMs
     */
    public IccdMsRenCertif getIccdRenouvelCertifMs() {
        return iccdRenouvelCertifMs;
    }

    /**
     * @param iccdRenouvelCertifMs the iccdRenouvelCertifMs to set
     */
    public void setIccdRenouvelCertifMs(IccdMsRenCertif iccdRenouvelCertifMs) {
        this.iccdRenouvelCertifMs = iccdRenouvelCertifMs;
    }

    /**
     * @return the iccdStructure
     */
    public IccdStructure getIccdStructure() {
        return iccdStructure;
    }

    /**
     * @param iccdStructure the iccdStructure to set
     */
    public void setIccdStructure(IccdStructure iccdStructure) {
        this.iccdStructure = iccdStructure;
    }

    

    public IccdFormaliteMinisterielle getIdFm() {
        return idFm;
    }

    public void setIdFm(IccdFormaliteMinisterielle idFm) {
        this.idFm = idFm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorique != null ? idHistorique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdHistoriqueFormalite)) {
            return false;
        }
        IccdHistoriqueFormalite other = (IccdHistoriqueFormalite) object;
        if ((this.idHistorique == null && other.idHistorique != null) || (this.idHistorique != null && !this.idHistorique.equals(other.idHistorique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdHistoriqueFormalite[ idHistorique=" + idHistorique + " ]";
    }
    
}
