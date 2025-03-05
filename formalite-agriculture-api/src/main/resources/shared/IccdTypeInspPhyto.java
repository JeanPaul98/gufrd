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
@Table(name = "ICCD_TYPE_INSPPHYTO", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_TYPE_INSPPHYTO",sequenceName = "S_ICCD_TYPE_INSPPHYTO", allocationSize = 1)
public class IccdTypeInspPhyto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_TYPE_INSPPHYTO")
    @Column(name = "ID_TYPE_INSPECTPHYTO")
    private Long idTypeInspectPhyto;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;


    public IccdTypeInspPhyto() {
    }

    public IccdTypeInspPhyto(Long idTypeInspectPhyto) {
        this.idTypeInspectPhyto = idTypeInspectPhyto;
    }

    public Long getIdTypeInspectPhyto() {
        return idTypeInspectPhyto;
    }

    public void setIdTypeInspectPhyto(Long idTypeInspectPhyto) {
        this.idTypeInspectPhyto = idTypeInspectPhyto;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idTypeInspectPhyto);
        hash = 97 * hash + Objects.hashCode(this.libelle);
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
        final IccdTypeInspPhyto other = (IccdTypeInspPhyto) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.idTypeInspectPhyto, other.idTypeInspectPhyto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdTypeInspPhyto{" + "idTypeInspectPhyto=" + idTypeInspectPhyto + ", libelle=" + libelle + '}';
    }
}
