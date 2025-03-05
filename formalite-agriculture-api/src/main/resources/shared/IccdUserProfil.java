/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdUserProfilPK;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author MJ
 */
@Entity
@Table(name = "ICCD_USER_PROFIL",schema = "SIPEDBA")
public class IccdUserProfil implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final short ACTIF = 1;
    
    private static final short INACTIF = 0;
    
    @EmbeddedId
    private IccdUserProfilPK userProfilPK;
    @Column(name = "ACTIF")
    private short actif;

    public IccdUserProfil() { 
        this.actif = ACTIF;
    }
    

    public IccdUserProfil(IccdUserProfilPK userProfilPK) {
        this.actif = ACTIF;
        this.userProfilPK = userProfilPK;
    }
    
    public IccdUserProfil(String profilCode, String userLogin){
        this.actif = ACTIF;
        this.userProfilPK =  new IccdUserProfilPK(profilCode,userLogin);        
    }
    
    
    
    /**
     * @return the userProfilPK
     */
    public IccdUserProfilPK getUserProfilPK() {
        return userProfilPK;
    }

    /**
     * @param userProfilPK the userProfilPK to set
     */
    public void setUserProfilPK(IccdUserProfilPK userProfilPK) {
        this.userProfilPK = userProfilPK;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.userProfilPK);
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
        final IccdUserProfil other = (IccdUserProfil) obj;
        if (!Objects.equals(this.userProfilPK, other.userProfilPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdUserProfil{" + "userProfilPK=" + userProfilPK + '}';
    }
    
    
    
    
    
}
