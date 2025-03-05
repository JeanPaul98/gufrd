/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdProcesverbal;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_DET_PROCES_VERBAL")
@SequenceGenerator(name = "S_ICCD_DET_PROCES_VERBAL", sequenceName = "S_ICCD_DET_PROCES_VERBAL", allocationSize = 1)
@XmlRootElement
public class IccdDetProcesVerbal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_DET_PROCES_VERBAL")
    @Column(name = "ID_DET_PV")
    private Long IdDetPV;
        
    @JoinColumn(name = "ID_PROCES_VERBAL", referencedColumnName = "ID_PV")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdProcesverbal iccdProcesVerbal;

    @Size(max = 254)
    @Column(name = "DESCRIPTION_ENVOI")
    private String descriptionEnvoi;
    
    @Size(max = 254)
    @Column(name = "PAYS_ET_LIEU_ORIGINE")
    private String paysEtLieuOrigin;

    @Column(name = "QUANTITE")
    private int quantite;
    
    @Size(max = 254)
    @Column(name = "VARIETE")
    private String varietes;
    
    @Size(max = 254)
    @Column(name = "EMPLACEMENT")
    private String emplacement;
    
    @Size(max = 254)
    @Column(name = "MESURE")
    private String mesure;
    
    @Size(max = 254)
    @Column(name = "NATURE")
    private String nature;


    public IccdDetProcesVerbal() {
    }

    public IccdDetProcesVerbal(Long IdDetPV) {
        this.IdDetPV = IdDetPV;
    }

    public Long getIdDetPV() {
        return IdDetPV;
    }

    public void setIdDetPV(Long IdDetPV) {
        this.IdDetPV = IdDetPV;
    }

    public com.acl.iccd.core.entities.IccdProcesverbal getIccdProcesVerbal() {
        return iccdProcesVerbal;
    }

    public void setIccdProcesVerbal(IccdProcesverbal iccdProcesVerbal) {
        this.iccdProcesVerbal = iccdProcesVerbal;
    }

    public String getDescriptionEnvoi() {
        return descriptionEnvoi;
    }

    public void setDescriptionEnvoi(String descriptionEnvoi) {
        this.descriptionEnvoi = descriptionEnvoi;
    }

    public String getPaysEtLieuOrigin() {
        return paysEtLieuOrigin;
    }

    public void setPaysEtLieuOrigin(String paysEtLieuOrigin) {
        this.paysEtLieuOrigin = paysEtLieuOrigin;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getVarietes() {
        return varietes;
    }

    public void setVarietes(String varietes) {
        this.varietes = varietes;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getMesure() {
        return mesure;
    }

    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.IdDetPV);
        hash = 29 * hash + Objects.hashCode(this.iccdProcesVerbal);
        hash = 29 * hash + Objects.hashCode(this.descriptionEnvoi);
        hash = 29 * hash + Objects.hashCode(this.paysEtLieuOrigin);
        hash = 29 * hash + this.quantite;
        hash = 29 * hash + Objects.hashCode(this.varietes);
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
        final IccdDetProcesVerbal other = (IccdDetProcesVerbal) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.descriptionEnvoi, other.descriptionEnvoi)) {
            return false;
        }
        if (!Objects.equals(this.paysEtLieuOrigin, other.paysEtLieuOrigin)) {
            return false;
        }
        if (!Objects.equals(this.varietes, other.varietes)) {
            return false;
        }
        if (!Objects.equals(this.IdDetPV, other.IdDetPV)) {
            return false;
        }
        if (!Objects.equals(this.iccdProcesVerbal, other.iccdProcesVerbal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdDetProcesVerbal{" + "IdDetPV=" + IdDetPV + ", iccdProcesVerbal=" + iccdProcesVerbal + ", descriptionEnvoi=" + descriptionEnvoi + ", paysEtLieuOrigin=" + paysEtLieuOrigin + ", quantite=" + quantite + ", varietes=" + varietes + '}';
    }
}
