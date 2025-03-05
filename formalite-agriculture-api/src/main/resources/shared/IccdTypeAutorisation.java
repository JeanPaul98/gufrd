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
@Table(name = "ICCD_TYPE_AUTORISATION", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_TYPE_AUTORISATION",sequenceName = "S_ICCD_TYPE_AUTORISATION", allocationSize = 1)
@XmlRootElement
public class IccdTypeAutorisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_TYPE_AUTORISATION")
    @Column(name = "ID_TYPE_AUTORISATION")
    private Long idTypeAutorisation;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle;
    

    public IccdTypeAutorisation() {
    }

    public IccdTypeAutorisation(Long idtypeautorisation) {
        this.idTypeAutorisation = idtypeautorisation;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelletypeautorisation(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdTypeAutorisation() {
        return idTypeAutorisation;
    }

    public void setIdTypeAutorisation(Long idTypeAutorisation) {
        this.idTypeAutorisation = idTypeAutorisation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idTypeAutorisation);
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
        final IccdTypeAutorisation other = (IccdTypeAutorisation) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.idTypeAutorisation, other.idTypeAutorisation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdTypeAutorisation{" + "idTypeAutorisation=" + idTypeAutorisation + ", libelle=" + libelle + '}';
    }
    
}
