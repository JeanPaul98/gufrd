/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.IccdProduit;
import com.acl.iccd.core.entities.IccdTypeAutorisation;
import com.acl.iccd.core.entities.Navire;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_AUTORISATION", catalog = "", schema = "SIPEDBA")
@XmlRootElement
@SequenceGenerator(name = "S_ICCD_AUTORISATION",sequenceName = "S_ICCD_AUTORISATION", allocationSize = 1)
public class IccdAutorisation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_AUTORISATION")
    @Column(name = "ID_AUTORISATION")
    private Long idAutorisation;
    
    @Size(max = 254)
    @Column(name = "NUM_ATP")
    private String numATP;
       
    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;
    
    @Size(max = 254)
    @Column(name = "PRODUITS")
    private String produits;   
     
    @Column(name = "QUANTITE")
    private Double quantite;
    @Size(max = 254)
    @Column(name = "PROVENANCE")
    private String provenance;
    @Size(max = 254)
    @Column(name = "NATURE_PRODUIT")
    private String natureproduit;
    @Column(name = "POIDS_NET")
    private Double poidsnet;
    @Size(max = 254)
    @Column(name = "DESIGNATION")
    private String designation;
    @Size(max = 254)
    @Column(name = "ORIGINE")
    private String origine;
    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    @ManyToOne
    private Navire navire;
    @Column(name = "DATE_EMBARQUEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateembarquement;
    @Column(name = "NOMBRE_CARTON")
    private int nombrecarton;
    @Column(name = "DATE_ARRIVEE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;
    @Column(name = "NOMBRE_TOTAL")
    private int nombretotal;
    @Column(name = "DATE_PRODUCTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateproduction;
    @Size(max = 254)
    @Column(name = "CONTENEUR")
    private String conteneur;

    @Size(max = 254)
    @Column(name = "TYPE_DECHETS")
    private String typedechets;

    @Size(max = 254)
    @Column(name = "MOTIF_REFUS")
    private String motifRefus;

    @Size(max = 254)
    @Column(name = "OBSERVATION")
    private String observation;


    @Column(name = "ACCORDER")
    private int accorder;
    
    @Column(name = "MONTANT_REDEVANCE")
    private Double montantRedevance = 100000.0;
    
    @Column(name = "REDEVANCE_PAYER")
    private boolean redevancePayer;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formalite;
    
    @JoinColumn(name = "ID_TYPE_AUTORISATION", referencedColumnName = "ID_TYPE_AUTORISATION")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdTypeAutorisation typeAutorisation;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdProduit iccdProduit;

    public IccdAutorisation() {
        redevancePayer = false;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public IccdAutorisation(Long idAutorisation) {
        this.idAutorisation = idAutorisation;
    }

    public Long getIdAutorisation() {
        return idAutorisation;
    }

    public void setIdAutorisation(Long idAutorisation) {
        this.idAutorisation = idAutorisation;
    }

    public String getProduits() {
        return produits;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getNatureproduit() {
        return natureproduit;
    }

    public void setNatureproduit(String natureproduit) {
        this.natureproduit = natureproduit;
    }

    public Double getPoidsnet() {
        return poidsnet;
    }

    public void setPoidsnet(Double poidsnet) {
        this.poidsnet = poidsnet;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Date getDateembarquement() {
        return dateembarquement;
    }

    public void setDateembarquement(Date dateembarquement) {
        this.dateembarquement = dateembarquement;
    }

    public int getNombrecarton() {
        return nombrecarton;
    }

    public void setNombrecarton(int nombrecarton) {
        this.nombrecarton = nombrecarton;
    }

    public Date getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(Date datearrivee) {
        this.datearrivee = datearrivee;
    }

    public int getNombretotal() {
        return nombretotal;
    }

    public void setNombretotal(int nombretotal) {
        this.nombretotal = nombretotal;
    }

    public Date getDateproduction() {
        return dateproduction;
    }

    public void setDateproduction(Date dateproduction) {
        this.dateproduction = dateproduction;
    }

    public String getConteneur() {
        return conteneur;
    }

    public void setConteneur(String conteneur) {
        this.conteneur = conteneur;
    }

    public String getTypedechets() {
        return typedechets;
    }

    public void setTypedechets(String typedechets) {
        this.typedechets = typedechets;
    }

    public IccdFormaliteMinisterielle getFormalite() {
        return formalite;
    }

    public void setFormalite(IccdFormaliteMinisterielle formalite) {
        this.formalite = formalite;
    }

    public Navire getNavire() {
        return navire;
    }

    public void setNavire(Navire navire) {
        this.navire = navire;
    }

    public com.acl.iccd.core.entities.IccdTypeAutorisation getTypeAutorisation() {
        return typeAutorisation;
    }

    public void setTypeAutorisation(IccdTypeAutorisation typeAutorisation) {
        this.typeAutorisation = typeAutorisation;
    }

    public Double getMontantRedevance() {
        return montantRedevance;
    }

    public void setMontantRedevance(Double montantRedevance) {
        this.montantRedevance = montantRedevance;
    }

    public boolean isRedevancePayer() {
        return redevancePayer;
    }

    public void setRedevancePayer(boolean redevancePayer) {
        this.redevancePayer = redevancePayer;
    }

    public com.acl.iccd.core.entities.IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public int getAccorder() {
        return accorder;
    }

    public void setAccorder(int accorder) {
        this.accorder = accorder;
    }

    public String getNumATP() {
        return numATP;
    }

    public void setNumATP(String numATP) {
        this.numATP = numATP;
    }

    public String getNumGenere() {
        return numGenere;
    }

    public void setNumGenere(String numGenere) {
        this.numGenere = numGenere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutorisation != null ? idAutorisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdAutorisation)) {
            return false;
        }
        IccdAutorisation other = (IccdAutorisation) object;
        if ((this.idAutorisation == null && other.idAutorisation != null) || (this.idAutorisation != null && !this.idAutorisation.equals(other.idAutorisation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdAutorisation[ idAutorisation=" + idAutorisation + " ]";
    }
    
}
