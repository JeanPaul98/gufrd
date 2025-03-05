/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdMaPhytoSanitaire;
import com.acl.iccd.core.entities.IccdProduit;

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
@Table(name = "ICCD_DET_INSP_PYTHOSAN_IMPRT")
@SequenceGenerator(name = "S_ICCD_DET_INSP_PYTHOSAN", sequenceName = "S_ICCD_DET_INSP_PYTHOSAN", allocationSize = 1)
@XmlRootElement
public class IccdMaDetInspPhytoSanitaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_DET_INSP_PYTHOSAN")
    @Column(name = "ID_DET_INSP_PYTHO")
    private Long idDetInspPhytosanImport;

    @JoinColumn(name = "ID_MA_INSPECTION", referencedColumnName = "ID_MA_INSPECTION")
    @ManyToOne
    private IccdMaPhytoSanitaire iccdMaInspPhytoSanitaire;

    @Size(max = 254)
    @Column(name = "DESCRIPTION_ENVOI")
    private String descriptionEnvoi;
    
    @Size(max = 254)
    @Column(name = "PAYS_ET_LIEU_ORIGINE")
    private String paysEtLieuOrigin;

    @Column(name = "QUANTITE")
    private int quantite;
    
    @Size(max = 254)
    @Column(name = "FOURNISSEUR")
    private String fournisseur;
    
    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private IccdProduit iccdProduit;

    public IccdMaDetInspPhytoSanitaire() {
    }

    public IccdMaDetInspPhytoSanitaire(Long idDetInspPhytosanImport) {
        this.idDetInspPhytosanImport = idDetInspPhytosanImport;
    }

    public Long getIdDetInspPhytosanImport() {
        return idDetInspPhytosanImport;
    }

    public void setIdDetInspPhytosanImport(Long idDetInspPhytosanImport) {
        this.idDetInspPhytosanImport = idDetInspPhytosanImport;
    }

    public IccdMaPhytoSanitaire getIccdMaInspPhytoSanitaire() {
        return iccdMaInspPhytoSanitaire;
    }

    public void setIccdMaInspPhytoSanitaire(IccdMaPhytoSanitaire iccdMaInspPhytoSanitaire) {
        this.iccdMaInspPhytoSanitaire = iccdMaInspPhytoSanitaire;
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

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idDetInspPhytosanImport);
        hash = 59 * hash + Objects.hashCode(this.descriptionEnvoi);
        hash = 59 * hash + Objects.hashCode(this.paysEtLieuOrigin);
        hash = 59 * hash + this.quantite;
        hash = 59 * hash + Objects.hashCode(this.fournisseur);
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
        final IccdMaDetInspPhytoSanitaire other = (IccdMaDetInspPhytoSanitaire) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.descriptionEnvoi, other.descriptionEnvoi)) {
            return false;
        }
        if (!Objects.equals(this.paysEtLieuOrigin, other.paysEtLieuOrigin)) {
            return false;
        }
        if (!Objects.equals(this.fournisseur, other.fournisseur)) {
            return false;
        }
        if (!Objects.equals(this.idDetInspPhytosanImport, other.idDetInspPhytosanImport)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IccdMaDetInspPhytoSanitaire{" + "idDetInspPhytosanImport=" + idDetInspPhytosanImport + ", iccdMaInspPhytoSanitaire=" + iccdMaInspPhytoSanitaire + ", descriptionEnvoi=" + descriptionEnvoi + ", paysEtLieuOrigin=" + paysEtLieuOrigin + ", quantite=" + quantite + ", fournisseur=" + fournisseur + ", iccdProduit=" + iccdProduit + '}';
    }
}
