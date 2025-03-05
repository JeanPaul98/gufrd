/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.IccdMsTypeCtrl;

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
@Table(name = "ICCD_MS_CTRL_SAN", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_CTRL_SAN",sequenceName = "S_ICCD_MS_CTRL_SAN", allocationSize = 1)
public class IccdMsCtrlSan implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_CTRL_SAN")
    @Column(name = "ID_DESINFECTION")
    private Long idDesinfection;
    @Size(max = 254)
    @Column(name = "NOM_NAVIRE")
    private String nomNavire;
    @Size(max = 254)
    @Column(name = "PAVILLON")
    private String pavillon;
    @Size(max = 254)
    @Column(name = "NUMERO_IDENT")
    private String numeroIdent;
    @Size(max = 254)
    @Column(name = "NRT")
    private String nrt;
    @Column(name = "NBRE_TONNES")
    private long nbreTonnes;
    @Column(name = "NBRE_CARGAISON")
    private long nbreCargaison;
    @Size(max = 254)
    @Column(name = "NOM_AGENT")
    private String nomAgent;
    @Column(name = "DATE_REINSPECTION")
    @Temporal(TemporalType.DATE)
    private Date dateReinspection;
    @Size(max = 254)
    @Column(name = "REMARQUES")
    private String remarques;
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formaliteMinisterielle;
    
    @JoinColumn(name = "ID_TYPE_CONTROLE", referencedColumnName = "ID_TYPE_CTRL")
    @ManyToOne
    private IccdMsTypeCtrl typeCtrl;
    

    public IccdMsCtrlSan() {
    }

    public IccdMsCtrlSan(Long idDesinfection) {
        this.idDesinfection = idDesinfection;
    }

    public Long getIdDesinfection() {
        return idDesinfection;
    }

    public void setIdDesinfection(Long idDesinfection) {
        this.idDesinfection = idDesinfection;
    }

    public String getPavillon() {
        return pavillon;
    }

    public void setPavillon(String pavillon) {
        this.pavillon = pavillon;
    }

    public String getNrt() {
        return nrt;
    }

    public void setNrt(String nrt) {
        this.nrt = nrt;
    }
    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    /**
     * @return the nomNavire
     */
    public String getNomNavire() {
        return nomNavire;
    }

    /**
     * @param nomNavire the nomNavire to set
     */
    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    /**
     * @return the numeroIdent
     */
    public String getNumeroIdent() {
        return numeroIdent;
    }

    /**
     * @param numeroIdent the numeroIdent to set
     */
    public void setNumeroIdent(String numeroIdent) {
        this.numeroIdent = numeroIdent;
    }

    /**
     * @return the nbreTonnes
     */
    public long getNbreTonnes() {
        return nbreTonnes;
    }

    /**
     * @param nbreTonnes the nbreTonnes to set
     */
    public void setNbreTonnes(long nbreTonnes) {
        this.nbreTonnes = nbreTonnes;
    }

    /**
     * @return the nbreCargaison
     */
    public long getNbreCargaison() {
        return nbreCargaison;
    }

    /**
     * @param nbreCargaison the nbreCargaison to set
     */
    public void setNbreCargaison(long nbreCargaison) {
        this.nbreCargaison = nbreCargaison;
    }

    /**
     * @return the nomAgent
     */
    public String getNomAgent() {
        return nomAgent;
    }

    /**
     * @param nomAgent the nomAgent to set
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
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
     * @return the formaliteMinisterielle
     */
    public IccdFormaliteMinisterielle getFormaliteMinisterielle() {
        return formaliteMinisterielle;
    }

    /**
     * @param formaliteMinisterielle the formaliteMinisterielle to set
     */
    public void setFormaliteMinisterielle(IccdFormaliteMinisterielle formaliteMinisterielle) {
        this.formaliteMinisterielle = formaliteMinisterielle;
    }

    /**
     * @return the typeCtrl
     */
    public IccdMsTypeCtrl getTypeCtrl() {
        return typeCtrl;
    }

    /**
     * @param typeCtrl the typeCtrl to set
     */
    public void setTypeCtrl(IccdMsTypeCtrl typeCtrl) {
        this.typeCtrl = typeCtrl;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDesinfection != null ? idDesinfection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsCtrlSan)) {
            return false;
        }
        IccdMsCtrlSan other = (IccdMsCtrlSan) object;
        if ((this.idDesinfection == null && other.idDesinfection != null) || (this.idDesinfection != null && !this.idDesinfection.equals(other.idDesinfection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsCtrlSan[ idDesinfection=" + idDesinfection + " ]";
    }
    
}
