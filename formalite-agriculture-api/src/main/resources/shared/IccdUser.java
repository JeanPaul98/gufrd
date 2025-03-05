/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.Client;
import com.acl.iccd.core.entities.IccdStructure;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MJ
 */
@Entity
@Table(name = "ICCD_USER",schema = "SIPEDBA")
public class IccdUser implements Serializable {

    private static final long serialVersionUID = 1L;
        
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ICCD_USER_LOGIN")
    private String login;    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ICCD_USER_PASSWORD")
    private String password;    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ICCD_USER_FULLNAME")
    private String nom;    
    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Column(name = "ACTIF")
    private short actif;
    @Column(name = "IS_ADMIN_USER")
    private short IsAdminUser;
    @Column(name = "DEFAULT_PASSWD")
    private short defaultPasswd;
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT")
    @ManyToOne
    private Client client;
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "ID_STRUCTURE")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdStructure structure;
        
    @Transient
    private String oldPassword;
    @Transient
    private String newPassword;
    @Transient
    private String confirmPassword;
    @Transient
    private String statutProfil;
    @Transient
    private String email;
    @Transient
    private Date connectedDate;
    
    public IccdUser() {
        
    }
    
    @PrePersist
    public void init(){
        actif = 1;
        IsAdminUser = 0;
        dateCreation = new Date();        
        if(client!=null){
            nom = client.getRaisonSocialClient();
        } else {
            if(nom==null){
               nom = " "; 
            }            
        }
        
    }
    
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * @param dateCreation the dateCreation to set
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * @return the actif
     */
    public short getActif() {
        return actif;
    }

    /**
     * @param actif the actif to set
     */
    public void setActif(short actif) {
        this.actif = actif;
    }

    /**
     * @return the IsAdminUser
     */
    public short getIsAdminUser() {
        return IsAdminUser;
    }

    /**
     * @param IsAdminUser the IsAdminUser to set
     */
    public void setIsAdminUser(short IsAdminUser) {
        this.IsAdminUser = IsAdminUser;
    }

    /**
     * @return the IccdStructure
     */
    public com.acl.iccd.core.entities.IccdStructure getStructure() {
        return structure;
    }

    /**
     * @param structure the structure to set
     */
    public void setStructure(IccdStructure structure) {
        this.structure = structure;
    }
    

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the statutProfil
     */
    public String getStatutProfil() {
        if(actif==0){
           setStatutProfil("Non"); 
        }else{
           setStatutProfil("Oui");  
        }
        return statutProfil;
    }

    /**
     * @param statutProfil the statutProfil to set
     */
    private void setStatutProfil(String statutProfil) {
        this.statutProfil = statutProfil;
    }

    /**
     * @return the defaultPasswd
     */
    public short getDefaultPasswd() {
        return defaultPasswd;
    }

    /**
     * @param defaultPasswd the defaultPasswd to set
     */
    public void setDefaultPasswd(short defaultPasswd) {
        this.defaultPasswd = defaultPasswd;
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
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getLogin() != null ? getLogin().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdUser)) {
            return false;
        }
        IccdUser other = (IccdUser) object;
        if ((this.getLogin() == null && other.getLogin() != null) || (this.getLogin() != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdUser[ login =" + getLogin() + " ]";
    }
    
}
