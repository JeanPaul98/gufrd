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
@Table(name = "ICCD_INSPECTEURS_PV")
@SequenceGenerator(name = "S_ICCD_INSPECTEURS_PV", sequenceName = "S_ICCD_INSPECTEURS_PV", allocationSize = 1)
@XmlRootElement
public class IccdInspecteursPV implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_INSPECTEURS_PV")
    @Column(name = "ID_INSPECTEURS_PV")
    private Long IdInspecteurPV;


    @Size(max = 254)
    @Column(name = "NOM_INSP")
    private String nomInspecteur;
    
    @Size(max = 254)
    @Column(name = "PRENOMS_INSP")
    private String prenomsInspecteur;

    @Size(max = 254)
    @Column(name = "NUMERO")
    private String numero;
    
    @Size(max = 254)
    @Column(name = "FONCTION")
    private String fonction;
    
    @Size(max = 254)
    @Column(name = "SIGNATURE")
    private String signature;

    public IccdInspecteursPV() {
    }

    public IccdInspecteursPV(Long IdInspecteurPV) {
        this.IdInspecteurPV = IdInspecteurPV;
    }

    public Long getIdInspecteurPV() {
        return IdInspecteurPV;
    }

    public void setIdInspecteurPV(Long IdInspecteurPV) {
        this.IdInspecteurPV = IdInspecteurPV;
    }

    public com.acl.iccd.core.entities.IccdProcesverbal getIccdProcesVerbal() {
        return iccdProcesVerbal;
    }

    public void setIccdProcesVerbal(IccdProcesverbal iccdProcesVerbal) {
        this.iccdProcesVerbal = iccdProcesVerbal;
    }

    public String getNomInspecteur() {
        return nomInspecteur;
    }

    public void setNomInspecteur(String nomInspecteur) {
        this.nomInspecteur = nomInspecteur;
    }

    public String getPrenomsInspecteur() {
        return prenomsInspecteur;
    }

    public void setPrenomsInspecteur(String prenomsInspecteur) {
        this.prenomsInspecteur = prenomsInspecteur;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.IdInspecteurPV);
        hash = 97 * hash + Objects.hashCode(this.iccdProcesVerbal);
        hash = 97 * hash + Objects.hashCode(this.nomInspecteur);
        hash = 97 * hash + Objects.hashCode(this.prenomsInspecteur);
        hash = 97 * hash + Objects.hashCode(this.numero);
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
        final IccdInspecteursPV other = (IccdInspecteursPV) obj;
        if (!Objects.equals(this.nomInspecteur, other.nomInspecteur)) {
            return false;
        }
        if (!Objects.equals(this.prenomsInspecteur, other.prenomsInspecteur)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.IdInspecteurPV, other.IdInspecteurPV)) {
            return false;
        }
        if (!Objects.equals(this.iccdProcesVerbal, other.iccdProcesVerbal)) {
            return false;
        }
        
        return true;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "IccdInspecteursPV{" + "IdInspecteurPV=" + IdInspecteurPV + ", iccdProcesVerbal=" + iccdProcesVerbal + ", nomInspecteur=" + nomInspecteur + ", prenomsInspecteur=" + prenomsInspecteur + ", numero=" + numero + ", signature=" + signature + '}';
    }
}
