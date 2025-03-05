/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author A.Jules <ahehehinnou@gmail.com>
 * @version 1.0
 * @since 04/03/2021
 */
@Entity
@Table(name = "ICCD_LAST_CLIENT",schema = "SIPEDBA")
public class IccdLastClient implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @Basic(optional = false)
    @Column(name = "ID_LAST_CLIENT")
    private Long idLastClient;
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
    @Basic(optional = false)
    @Column(name = "COMPTE_CLIENT")
    private String compteClient;
    
    /**
     * @return the idLastClient
     */
    public Long getIdLastClient() {
        return idLastClient;
    }

    /**
     * @param idLastClient the idLastClient to set
     */
    public void setIdLastClient(Long idLastClient) {
        this.idLastClient = idLastClient;
    }

    /**
     * @return the idClient
     */
    public Long getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdClient() != null ? getIdClient().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdLastClient)) {
            return false;
        }
        IccdLastClient other = (IccdLastClient) object;
        if ((this.getIdClient() == null && other.getIdClient() != null) || (this.getIdClient() != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdLastClient[ idClient=" + getIdClient() + " ]";
    }

}
