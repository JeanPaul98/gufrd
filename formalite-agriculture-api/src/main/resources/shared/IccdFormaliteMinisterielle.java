/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdMsRenCertif;
import com.acl.iccd.core.entities.IccdStructure;

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
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_FORMALITE_MINISTERIELLE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_FORMALITE_MINISTERIELLE",sequenceName = "S_ICCD_FORMALITE_MINISTERIELLE", allocationSize = 1)
public class IccdFormaliteMinisterielle implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_FORMALITE_MINISTERIELLE")
    @Column(name = "ID_FORMALITE")
    private Long idFormalite;
    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;
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
    @Column(name = "DECISION")
    private String decision;
    @Size(max = 254)
    @Column(name = "OBSERVATIONS")
    private String observations;
    @Column(name = "DATE_ANNULATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;
    @Size(max = 254)
    @Column(name = "MOTIF_ANNULATION")
    private String motifAnnulation;
    @Size(max = 254)
    @Column(name = "CHAINE")
    private String chaine;
    @Column(name = "utilisateur")
    private String utilisateur;

    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    @ManyToOne(optional = false)
    private com.acl.iccd.core.entities.IccdStructure iccdStructure;

    @Column(name = "MONTANT_REDEVANCE")
    private int montantRedevance;

    public IccdFormaliteMinisterielle(){
    }

    public IccdFormaliteMinisterielle(Long idFormalite) {
        this.idFormalite = idFormalite;
    }

    public short getEtat() {
        return etat;
    }

    public void setEtat(short etat) {
        this.etat = etat;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
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
     * @return the iccdRenouvelCertifMs
     */
    public com.acl.iccd.core.entities.IccdMsRenCertif getIccdRenouvelCertifMs() {
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
    public com.acl.iccd.core.entities.IccdStructure getIccdStructure() {
        return iccdStructure;
    }

    /**
     * @param iccdStructure the iccdStructure to set
     */
    public void setIccdStructure(IccdStructure iccdStructure) {
        this.iccdStructure = iccdStructure;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getChaine() {
        return chaine;
    }

    public void setChaine(String chaine) {
        this.chaine = chaine;
    }

    public Long getIdFormalite() {
        return idFormalite;
    }

    public void setIdFormalite(Long idFormalite) {
        this.idFormalite = idFormalite;
    }

    public int getMontantRedevance() {
        return montantRedevance;
    }

    public void setMontantRedevance(int montantRedevance) {
        this.montantRedevance = montantRedevance;
    }

    public String getCompteClient() {
        return compteClient;
    }

    public void setCompteClient(String compteClient) {
        this.compteClient = compteClient;
    }

    public String getNumGenere() {
        return numGenere;
    }

    public void setNumGenere(String numGenere) {
        this.numGenere = numGenere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormalite != null ? idFormalite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdFormaliteMinisterielle)) {
            return false;
        }
        IccdFormaliteMinisterielle other = (IccdFormaliteMinisterielle) object;
        if ((this.idFormalite == null && other.idFormalite != null) || (this.idFormalite != null && !this.idFormalite.equals(other.idFormalite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdFormaliteMinisterielle{" +
                "idFormalite=" + idFormalite +
                ", etat=" + etat +
                '}';
    }
}
