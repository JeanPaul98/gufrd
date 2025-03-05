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
@Table(name = "ICCD_MS_SERVICE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_SERVICE",sequenceName = "S_ICCD_MS_SERVICE", allocationSize = 1)
public class IccdMsService implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_SERVICE")
    @Column(name = "ID_MS_SERVICE")
    private Long idMsService;
    @Size(max = 254)
    @Column(name = "SIGNE_CONSTACTE")
    private String signeConstacte;
    @Size(max = 254)
    @Column(name = "RESULTAT_ANALYSE")
    private String resultatAnalyse;
    @Size(max = 254)
    @Column(name = "DOC_EXAMINE")
    private String docExamine;
    @Size(max = 254)
    @Column(name = "MESURE_LUTTE")
    private String mesureLutte;
    @Column(name = "DATE_REINSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReinspection;
    @Size(max = 254)
    @Column(name = "REMAQUES")
    private String remaques;    
    @JoinColumn(name = "ID_DESINFECT", referencedColumnName = "ID_DESINFECTION")
    @ManyToOne
    private IccdMsCtrlSan iccdMsCtrlSan;
    
    
    public IccdMsService() {
    }

    public IccdMsService(Long idMsService) {
        this.idMsService = idMsService;
    }

    public Long getIdMsService() {
        return idMsService;
    }

    public void setIdMsService(Long idMsService) {
        this.idMsService = idMsService;
    }

    /**
     * @return the signeConstacte
     */
    public String getSigneConstacte() {
        return signeConstacte;
    }

    /**
     * @param signeConstacte the signeConstacte to set
     */
    public void setSigneConstacte(String signeConstacte) {
        this.signeConstacte = signeConstacte;
    }

    /**
     * @return the resultatAnalyse
     */
    public String getResultatAnalyse() {
        return resultatAnalyse;
    }

    /**
     * @param resultatAnalyse the resultatAnalyse to set
     */
    public void setResultatAnalyse(String resultatAnalyse) {
        this.resultatAnalyse = resultatAnalyse;
    }

    /**
     * @return the docExamine
     */
    public String getDocExamine() {
        return docExamine;
    }

    /**
     * @param docExamine the docExamine to set
     */
    public void setDocExamine(String docExamine) {
        this.docExamine = docExamine;
    }

    /**
     * @return the mesureLutte
     */
    public String getMesureLutte() {
        return mesureLutte;
    }

    /**
     * @param mesureLutte the mesureLutte to set
     */
    public void setMesureLutte(String mesureLutte) {
        this.mesureLutte = mesureLutte;
    }

    /**
     * @return the dateReinspection
     */
    public Date getDateReinspection() {
        return dateReinspection;
    }

    /**
     * @param dateReinspection the dateReinspection to set
     */
    public void setDateReinspection(Date dateReinspection) {
        this.dateReinspection = dateReinspection;
    }

    /**
     * @return the remaques
     */
    public String getRemaques() {
        return remaques;
    }

    /**
     * @param remaques the remaques to set
     */
    public void setRemaques(String remaques) {
        this.remaques = remaques;
    }

    /**
     * @return the iccdMsCtrlSan
     */
    public IccdMsCtrlSan getIccdMsCtrlSan() {
        return iccdMsCtrlSan;
    }

    /**
     * @param iccdMsCtrlSan the iccdMsCtrlSan to set
     */
    public void setIccdMsCtrlSan(IccdMsCtrlSan iccdMsCtrlSan) {
        this.iccdMsCtrlSan = iccdMsCtrlSan;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsService != null ? idMsService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsService)) {
            return false;
        }
        IccdMsService other = (IccdMsService) object;
        if ((this.idMsService == null && other.idMsService != null) || (this.idMsService != null && !this.idMsService.equals(other.idMsService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsService[ idMsService=" + idMsService + " ]";
    }
    
}
