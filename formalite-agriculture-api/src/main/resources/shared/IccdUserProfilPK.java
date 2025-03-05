/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MJ
 */
@Embeddable
public class IccdUserProfilPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ICCD_PROFIL_CODE")
    private String profilCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ICCD_USER_LOGIN")
    private String userLogin;

    public IccdUserProfilPK() {
    }

    public IccdUserProfilPK(String profilCode, String userLogin) {
        this.profilCode = profilCode;
        this.userLogin = userLogin;
    }

   

    /**
     * @return the userLogin
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * @return the profilCode
     */
    public String getProfilCode() {
        return profilCode;
    }

    /**
     * @param profilCode the profilCode to set
     */
    public void setProfilCode(String profilCode) {
        this.profilCode = profilCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.profilCode);
        hash = 23 * hash + Objects.hashCode(this.userLogin);
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
        final IccdUserProfilPK other = (IccdUserProfilPK) obj;
        if (!Objects.equals(this.profilCode, other.profilCode)) {
            return false;
        }
        if (!Objects.equals(this.userLogin, other.userLogin)) {
            return false;
        }
        return true;
    }

    
    
}
