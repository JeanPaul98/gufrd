/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_MS_PARAM_SERVICE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_PARAM_SERVICE",sequenceName = "S_ICCD_MS_PARAM_SERVICE", allocationSize = 1)
@XmlRootElement
public class IccdMsParamService implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_PARAM_SERVICE")
    @Column(name = "ID_MS_PARAM_SERVICE")
    private Long idParamService;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;
    

    public IccdMsParamService() {
    }

    public IccdMsParamService(Long idParamService) {
        this.idParamService = idParamService;
    }

    public Long getIdMsParamService() {
        return idParamService;
    }

    public void setIdMsParamService(Long idParamService) {
        this.idParamService = idParamService;

    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParamService != null ? idParamService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsParamService)) {
            return false;
        }
        IccdMsParamService other = (IccdMsParamService) object;
        if ((this.idParamService == null && other.idParamService != null) || (this.idParamService != null && !this.idParamService.equals(other.idParamService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsParamService[ idParamService=" + idParamService + " ]";
    }
    
}
