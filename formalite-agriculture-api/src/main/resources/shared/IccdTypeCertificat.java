/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import java.util.Objects;
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
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_TYPE_CERTIFICAT", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_TYPE_CERTIFICAT",sequenceName = "S_ICCD_TYPE_CERTIFICAT", allocationSize = 1)
@XmlRootElement
public class IccdTypeCertificat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_TYPE_CERTIFICAT")
    @Column(name = "ID_TYPE_CERTIFICAT")
    private Long idTypeCertificat;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelleTypeCertificat;
    

    public IccdTypeCertificat() {
    }

    public IccdTypeCertificat(Long idtypecertificat) {
        this.idTypeCertificat = idtypecertificat;
    }

    public Long getIdTypeCertificat() {
        return idTypeCertificat;
    }

    public void setIdTypeCertificat(Long idTypeCertificat) {
        this.idTypeCertificat = idTypeCertificat;
    }

    public String getLibelleTypeCertificat() {
        return libelleTypeCertificat;
    }

    public void setLibelleTypeCertificat(String libelleTypeCertificat) {
        this.libelleTypeCertificat = libelleTypeCertificat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idTypeCertificat);
        hash = 59 * hash + Objects.hashCode(this.libelleTypeCertificat);
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
        final IccdTypeCertificat other = (IccdTypeCertificat) obj;
        if (!Objects.equals(this.libelleTypeCertificat, other.libelleTypeCertificat)) {
            return false;
        }
        if (!Objects.equals(this.idTypeCertificat, other.idTypeCertificat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdTypeCertificat{" + "idTypeCertificat=" + idTypeCertificat + ", libelleTypeCertificat=" + libelleTypeCertificat + '}';
    }
}
