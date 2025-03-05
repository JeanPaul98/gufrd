/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdMeDecDechets;
import com.acl.iccd.core.entities.Port;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_ME_DET_DECL_DECHETS")
@SequenceGenerator(name = "S_ICCD_ME_DET_DECL_DECHETS",sequenceName = "S_ICCD_ME_DET_DECL_DECHETS", allocationSize = 1)
@XmlRootElement
public class IccdMeDetDeclDechets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_ME_DET_DECL_DECHETS")
    @Column(name = "ID_DET_DECL_DECHET")
    private Long idDetDeclDechet;
    
    @JoinColumn(name = "ID_DECL", referencedColumnName = "ID_DECL")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdMeDecDechets declDechets;
    
    @Size(max = 254)
    @Column(name = "CATEGORIE_DECHETS")
    private String categorieDechets;
    
    @Column(name = "QTE_ABORD")
    private int qteAbord;
    
    @Column(name = "QTE_A_DECHARGER")
    private int qteADecharger;
    
    @Column(name = "CAPACITE_STOCKAGE")
    private int capaciteStockage;
    
    @Column(name = "QTE_RESTANT_ABORD")
    private int qteRestantAbord;
    
    @JoinColumn(name = "CODE_PROCHAIN_PORT_DECHARGMNT", referencedColumnName = "CODE_PORT")
    @ManyToOne
    private Port codeProchainPortDechargement;

    public IccdMeDetDeclDechets() {
    }

    public IccdMeDetDeclDechets(Long idDetDeclDechet) {
        this.idDetDeclDechet = idDetDeclDechet;
    }

    public Long getIdDetDeclDechet() {
        return idDetDeclDechet;
    }

    public void setIdDetDeclDechet(Long idDetDeclDechet) {
        this.idDetDeclDechet = idDetDeclDechet;
    }

    public String getCategorieDechets() {
        return categorieDechets;
    }

    public void setCategorieDechets(String categorieDechets) {
        this.categorieDechets = categorieDechets;
    }

    public int getQteAbord() {
        return qteAbord;
    }

    public void setQteAbord(int qteAbord) {
        this.qteAbord = qteAbord;
    }

    public int getQteADecharger() {
        return qteADecharger;
    }

    public void setQteADecharger(int qteADecharger) {
        this.qteADecharger = qteADecharger;
    }

    public int getCapaciteStockage() {
        return capaciteStockage;
    }

    public void setCapaciteStockage(int capaciteStockage) {
        this.capaciteStockage = capaciteStockage;
    }

    public int getQteRestantAbord() {
        return qteRestantAbord;
    }

    public void setQteRestantAbord(int qteRestantAbord) {
        this.qteRestantAbord = qteRestantAbord;
    }

    public com.acl.iccd.core.entities.IccdMeDecDechets getDeclDechets() {
        return declDechets;
    }

    public void setDeclDechets(IccdMeDecDechets declDechets) {
        this.declDechets = declDechets;
    }

    public Port getCodeProchainPortDechargement() {
        return codeProchainPortDechargement;
    }

    public void setCodeProchainPortDechargement(Port codeProchainPortDechargement) {
        this.codeProchainPortDechargement = codeProchainPortDechargement;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetDeclDechet != null ? idDetDeclDechet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMeDetDeclDechets)) {
            return false;
        }
        IccdMeDetDeclDechets other = (IccdMeDetDeclDechets) object;
        if ((this.idDetDeclDechet == null && other.idDetDeclDechet != null) || (this.idDetDeclDechet != null && !this.idDetDeclDechet.equals(other.idDetDeclDechet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMeDetDeclDechets[ idDetDeclDechet=" + idDetDeclDechet + " ]";
    }
    
}
