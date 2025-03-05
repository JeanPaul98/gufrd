/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdUser;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MJ
 */
@Entity
@Table(name = "ICCD_DMD_RESET_PWD",schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_DMD_RESET_PWD",sequenceName = "S_ICCD_DMD_RESET_PWD", allocationSize = 1)
public class IccdDmdResetPwd implements Serializable {

    private static final long serialVersionUID = 1L;
        
    @Id   
    //@Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_DMD_RESET_PWD")
    @Column(name = "ID_DMD_RESET_PWD")
    private Long idDmdResetPwd;  
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ETAT")
    private short etat;
    @Column(name = "DATE_DMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDmd;
    @JoinColumn(name = "ICCD_USER_LOGIN", referencedColumnName = "ICCD_USER_LOGIN")
    @ManyToOne(optional = false)
    private IccdUser user;

    public IccdDmdResetPwd() {
        
    }

    public IccdDmdResetPwd(String email, short etat, IccdUser user) {
        this.email = email;
        this.etat = etat;
        this.user = user;
    }
 
    
    
    @PrePersist
    public void init(){
       dateDmd = new Date(); 
    }
    
   /**
     * @return the idDmdResetPwd
     */
    public Long getIdDmdResetPwd() {
        return idDmdResetPwd;
    }

    /**
     * @param idDmdResetPwd the idDmdResetPwd to set
     */
    public void setIdDmdResetPwd(Long idDmdResetPwd) {
        this.idDmdResetPwd = idDmdResetPwd;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the user
     */
    public IccdUser getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(IccdUser user) {
        this.user = user;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDmdResetPwd != null ? idDmdResetPwd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdDmdResetPwd)) {
            return false;
        }
        IccdDmdResetPwd other = (IccdDmdResetPwd) object;
        if ((this.idDmdResetPwd == null && other.idDmdResetPwd != null) || (this.idDmdResetPwd != null && !this.idDmdResetPwd.equals(other.idDmdResetPwd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdDmdResetPwd[ idDmdResetPwd =" + idDmdResetPwd + " ]";
    }

    
    
}
