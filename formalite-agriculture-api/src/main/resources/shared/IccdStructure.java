/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.enumeration.TypeStructure;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_STRUCTURE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_STRUCTURE",sequenceName = "S_ICCD_STRUCTURE", allocationSize = 1)
@XmlRootElement
public class IccdStructure implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_STRUCTURE")
    @Column(name = "ID_STRUCTURE")
    private Long idStructure;
    @Size(max = 254)
    @Column(name = "CODE")
    private String code;
    @Size(max = 254)
    @Column(name = "LIBELLE")
    private String libelle; 
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_STRUCTURE")
    private TypeStructure typeStructure;    
    @JoinColumn(name = "ID_STRUCTURE_PARENTE", referencedColumnName = "ID_STRUCTURE")
    @ManyToOne
    private IccdStructure idStructureParent;
    

    public IccdStructure() {
    }

    public IccdStructure(Long idstructure) {
        this.idStructure = idstructure;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdStructure() {
        return idStructure;
    }

    public void setIdStructure(Long idStructure) {
        this.idStructure = idStructure;
    }

    public TypeStructure getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(TypeStructure typeStructure) {
        this.typeStructure = typeStructure;
    }

    public IccdStructure getIdStructureParent() {
        return idStructureParent;
    }

    public void setIdStructureParent(IccdStructure idStructureParent) {
        this.idStructureParent = idStructureParent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idStructure);
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
        final IccdStructure other = (IccdStructure) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.idStructure, other.idStructure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdStructure{" + "idStructure=" + idStructure + ", code=" + code + ", libelle=" + libelle + '}';
    }
    
}
