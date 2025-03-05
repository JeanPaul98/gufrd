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
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_TYPE_PV", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_TYPE_PV",sequenceName = "S_ICCD_TYPE_PV", allocationSize = 1)
public class IccdTypePv implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_TYPE_PV")
    @Column(name = "ID_TYPE_PV")
    private Long idTypePv;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;


    public IccdTypePv() {
    }

    public IccdTypePv(Long idTypePv) {
        this.idTypePv = idTypePv;
    }

    public Long getIdTypePv() {
        return idTypePv;
    }

    public void setIdTypePv(Long idTypePv) {
        this.idTypePv = idTypePv;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idTypePv);
        hash = 59 * hash + Objects.hashCode(this.libelle);
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
        final IccdTypePv other = (IccdTypePv) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.idTypePv, other.idTypePv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdTypePv{" + "idTypePv=" + idTypePv + ", libelle=" + libelle + '}';
    }

}
