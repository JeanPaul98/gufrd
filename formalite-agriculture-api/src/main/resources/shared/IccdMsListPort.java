/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import com.acl.iccd.core.entities.IccdMsDecMaritimeSante;
import com.acl.iccd.core.entities.Port;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

/**
 *
 * @author A.Jules <ahehehinnou@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ICCD_MS_LIST_PORT", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_LIST_PORT",sequenceName = "S_ICCD_MS_LIST_PORT", allocationSize = 1)
public class IccdMsListPort implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_LIST_PORT")
    @Column(name = "ID_LIST_PORT")
    private Long idListPort;
    @Column(name = "DATE_DEPART")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepart;
    @JoinColumn(name = "ID_DEC_SANTE", referencedColumnName = "ID_DEC_SANTE")
    @ManyToOne
    private IccdMsDecMaritimeSante declarationMaritimeSante;
    @JoinColumn(name = "CODE_PORT", referencedColumnName = "CODE_PORT")
    @ManyToOne
    private Port port;

    /**
     * @return the idListPort
     */
    public Long getIdListPort() {
        return idListPort;
    }

    /**
     * @param idListPort the idListPort to set
     */
    public void setIdListPort(Long idListPort) {
        this.idListPort = idListPort;
    }

    /**
     * @return the dateDepart
     */
    public Date getDateDepart() {
        return dateDepart;
    }

    /**
     * @param dateDepart the dateDepart to set
     */
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    /**
     * @return the declarationMaritimeSante
     */
    public IccdMsDecMaritimeSante getDeclarationMaritimeSante() {
        return declarationMaritimeSante;
    }

    /**
     * @param declarationMaritimeSante the declarationMaritimeSante to set
     */
    public void setDeclarationMaritimeSante(IccdMsDecMaritimeSante declarationMaritimeSante) {
        this.declarationMaritimeSante = declarationMaritimeSante;
    }

    /**
     * @return the port
     */
    public Port getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Port port) {
        this.port = port;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idListPort);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IccdMsListPort other = (IccdMsListPort) obj;
        if (!Objects.equals(this.idListPort, other.idListPort)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdMsListPort{" + "idListPort=" + idListPort + '}';
    }
    
    
    
    
}
