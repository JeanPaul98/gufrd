/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_MA_PHYTOSANITAIRE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MA_PHYTOSANITAIRE",sequenceName = "S_ICCD_MA_PHYTOSANITAIRE", allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IccdMaPhytoSanitaire.findAll", query = "SELECT i FROM IccdMaPhytoSanitaire i")})
public class IccdMaPhytoSanitaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MA_PHYTOSANITAIRE")
    @Column(name = "ID_MA_INSPECTION")
    private Long idMaInspection;
    
    @Size(max = 254)
    @Column(name = "NUM_ATP")
    private String numATP;
       
    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;
    
    @Size(max = 254)
    @Column(name = "NOM_DEMANDEUR")
    private String nomDemandeur;
    
    @Size(max = 254)
    @Column(name = "PROFESSION_DEMANDEUR")
    private String professionDemandeur;
    
    @Size(max = 254)
    @Column(name = "ADRESSE_DEMANDEUR")
    private String adresseDemandeur;
    
    @Size(max = 254)
    @Column(name = "TYPE_INSPECTION")
    private String typeInspection;
    
    @Column(name = "DATE_PREVUE_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePrevueInspection;

    @Size(max = 254)
    @Column(name = "LIEU_INSPECTION")
    private String lieuInspection;
    
    @Column(name = "DATE_DEPART_PREUVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepartPreuve;
        
    @Size(max = 254)
    @Column(name = "NOM_EXPEDITEUR")
    private String nomExpediteur;
    
    @Size(max = 254)
    @Column(name = "ADRESSE_EXPEDITEUR")
    private String adresseExpediteur;
    
    @Column(name = "QTE_OU_POIDS_TOTAL")
    private Double qteouPoidsTotal;
    
    @Column(name = "NBE_UNITES")
    private int nbeUnites;
    
    @Size(max = 254)
    @Column(name = "LIEU_CHARGEMENT")
    private String lieuChargement;
    
    @Size(max = 254)
    @Column(name = "LIEU_DESTINATION")
    private String lieuDestination;
    
    @Size(max = 254)
    @Column(name = "NOM_DESTINATAIRE")
    private String nomDestinataire;
    
    @Size(max = 254)
    @Column(name = "CONTENEUR")
    private String conteneur;
        
    @Size(max = 254)
    @Column(name = "ADRESSE_DESTINATAIRE")
    private String adresseDestinataire;
    
    @Column(name = "NOMBRE_COLIS")
    private int nombreColis;
    
    @Column(name = "QUANTITE")
    private Double quantite;
    
    @Size(max = 254)
    @Column(name = "LIEU_ORIGINE")
    private String lieuOrigine;
    
    @Size(max = 254)
    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;
    
    @Size(max = 254)
    @Column(name = "POINT_ENTREE")
    private String pointEntree;
    
    @Column(name = "DATE_HEURE_EMBARQUEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureEmbarquement;
    
    @Size(max = 254)
    @Column(name = "NOM_SOC_TRAITEMENT")
    private String nomSocTraitement;
    
    @Size(max = 254)
    @Column(name = "CONTACT_SOCIETE")
    private String contactSociete;

    @Column(name = "TRAITEMENT_PRODUIT")
    private String traitementProduit;
    
    @Column(name = "RENSEIGNEMENT_COMPL")
    private String renseignementCompl;
    
    @Column(name = "ACCORDER")
    private int accorder;
    
    @Size(max = 254)
    @Column(name = "MOTIF_REFUS")
    private String motifRefus;
    
    @Size(max = 254)
    @Column(name = "NOM_NAVIRE")
    private String nomNavire;
   
    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;
    
    @Column(name = "HEURE_DEB_TRAIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebTrait;
    
    @Size(max = 254)
    @Column(name = "NOM_COMMERCIAL_PRDT")
    private String nomCommercialPrdt;
    
    @Size(max = 254)
    @Column(name = "SUBSTANCE_ACTIVE")
    private String substanceActive;
    
    @Size(max = 254)
    @Column(name = "CONCENTRATION")
    private String concentration;
    
    @Column(name = "DUREE")
    private int duree;
    
    @Column(name = "TEMPERATURE")
    private Double temperature;
    
    @Size(max = 254)
    @Column(name = "RENSEIGN_COMPL")
    private String renseignCompl;
    
    @Size(max = 254)
    @Column(name = "DECL_SUPPL")
    private String declSuppl;
    
    @Size(max = 254)
    @Column(name = "NOM_INSPECTEUR")
    private String nomInspecteur;
    
    @Size(max = 254)
    @Column(name = "VOL")
    private String vol;
    @Size(max = 254)
    @Column(name = "NUM_CAMION")
    private String numCamion;
    @Size(max = 254)
    @Column(name = "MAGASIN_PROV")
    private String magasinProv;
    @Size(max = 254)
    @Column(name = "DATE_DELIVRANCE")
    private String dateDelivrance;
    @Size(max = 254)
    @Column(name = "LIEU_DELIVRANCE")
    private String lieuDelivrance;
    @Column(name = "DATE_SIGNATURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSignature;
    
    @Size(max = 254)
    @Column(name = "AFFRETTEUR")
    private String affreteur;
    @Size(max = 254)
    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "DATE_ARRIVEE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrivee;
    @Column(name = "DATE_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInspection;

    @Size(max = 254)
    @Column(name = "NATURE")
    private String nature;
    
    @Size(max = 254)
    @Column(name = "OFFICIER_NAVIRE_PRESENT")
    private String officierNavirePresent;
    
    @Size(max = 254)
    @Column(name = "PARTIE_NAVIRE_VISITE")
    private String partieNavireVisite;
    
    @Size(max = 254)
    @Column(name = "MESURE")
    private String mesure;
    @Size(max = 254)
    @Column(name = "EMPLACEMENTS")
    private String emplacements;

    @Size(max = 254)
    @Column(name = "REMARQUES_PARTICULIERES")
    private String remarquesParticulieres;
    @Size(max = 254)
    @Column(name = "DESCRIPTION_PRODUIT")
    private String descriptionProduit;
    @Size(max = 254)
    @Column(name = "NOM_SCIENTIFIQUE")
    private String nomScientifique;
    @Size(max = 254)
    @Column(name = "TEMPERATURE_ENTREPOSAGE")
    private String temperatureEntreposage;

    @Size(max = 254)
    @Column(name = "AGREMENT")
    private String agrement;

    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    @ManyToOne
    private Navire navire;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private com.acl.iccd.core.shared.oldEntities.IccdFormaliteMinisterielle formalite;
    
    @JoinColumn(name = "ID_TYPE_INSPECT_PHYTO", referencedColumnName = "ID_TYPE_INSPECTPHYTO")
    @ManyToOne
    private com.acl.iccd.core.shared.oldEntities.IccdTypeInspPhyto idTypeInspectPhyto;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private com.acl.iccd.core.shared.oldEntities.IccdProduit iccdProduit;


    public IccdMaPhytoSanitaire() {
    }

    public IccdMaPhytoSanitaire(Long idMaInspection) {
        this.idMaInspection = idMaInspection;
    }

    public Long getIdMaInspection() {
        return idMaInspection;
    }

    public void setIdMaInspection(Long idMaInspection) {
        this.idMaInspection = idMaInspection;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public String getAgrement() {
        return agrement;
    }

    public void setAgrement(String agrement) {
        this.agrement = agrement;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
    }

    public String getTemperatureEntreposage() {
        return temperatureEntreposage;
    }

    public void setTemperatureEntreposage(String temperatureEntreposage) {
        this.temperatureEntreposage = temperatureEntreposage;
    }

    public int getAccorder() {
        return accorder;
    }

    public void setAccorder(int accorder) {
        this.accorder = accorder;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getNomExpediteur() {
        return nomExpediteur;
    }

    public void setNomExpediteur(String nomExpediteur) {
        this.nomExpediteur = nomExpediteur;
    }

    public String getAdresseExpediteur() {
        return adresseExpediteur;
    }

    public void setAdresseExpediteur(String adresseExpediteur) {
        this.adresseExpediteur = adresseExpediteur;
    }

    public Double getQteouPoidsTotal() {
        return qteouPoidsTotal;
    }

    public void setQteouPoidsTotal(Double qteouPoidsTotal) {
        this.qteouPoidsTotal = qteouPoidsTotal;
    }

    public int getNbeUnites() {
        return nbeUnites;
    }

    public void setNbeUnites(int nbeUnites) {
        this.nbeUnites = nbeUnites;
    }

    public String getLieuChargement() {
        return lieuChargement;
    }

    public void setLieuChargement(String lieuChargement) {
        this.lieuChargement = lieuChargement;
    }

    public String getLieuDestination() {
        return lieuDestination;
    }

    public void setLieuDestination(String lieuDestination) {
        this.lieuDestination = lieuDestination;
    }

    public String getConteneur() {
        return conteneur;
    }

    public void setConteneur(String conteneur) {
        this.conteneur = conteneur;
    }

    public String getTypeInspection() {
        return typeInspection;
    }

    public void setTypeInspection(String typeInspection) {
        this.typeInspection = typeInspection;
    }

    public String getNomDestinataire() {
        return nomDestinataire;
    }

    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    public String getAdresseDestinataire() {
        return adresseDestinataire;
    }

    public void setAdresseDestinataire(String adresseDestinataire) {
        this.adresseDestinataire = adresseDestinataire;
    }

    public int getNombreColis() {
        return nombreColis;
    }

    public void setNombreColis(int nombreColis) {
        this.nombreColis = nombreColis;
    }

    public String getLieuOrigine() {
        return lieuOrigine;
    }

    public void setLieuOrigine(String lieuOrigine) {
        this.lieuOrigine = lieuOrigine;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public String getPointEntree() {
        return pointEntree;
    }

    public void setPointEntree(String pointEntree) {
        this.pointEntree = pointEntree;
    }

    public Date getDateHeureEmbarquement() {
        return dateHeureEmbarquement;
    }

    public void setDateHeureEmbarquement(Date dateHeureEmbarquement) {
        this.dateHeureEmbarquement = dateHeureEmbarquement;
    }

    public String getNomSocTraitement() {
        return nomSocTraitement;
    }

    public void setNomSocTraitement(String nomSocTraitement) {
        this.nomSocTraitement = nomSocTraitement;
    }

    public String getContactSociete() {
        return contactSociete;
    }

    public void setContactSociete(String contactSociete) {
        this.contactSociete = contactSociete;
    }

    public String getTraitementProduit() {
        return traitementProduit;
    }

    public void setTraitementProduit(String traitementProduit) {
        this.traitementProduit = traitementProduit;
    }

    public String getRenseignementCompl() {
        return renseignementCompl;
    }

    public void setRenseignementCompl(String renseignementCompl) {
        this.renseignementCompl = renseignementCompl;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public String getNomNavire() {
        return nomNavire;
    }

    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    public Date getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Date getHeureDebTrait() {
        return heureDebTrait;
    }

    public void setHeureDebTrait(Date heureDebTrait) {
        this.heureDebTrait = heureDebTrait;
    }

    public String getNomCommercialPrdt() {
        return nomCommercialPrdt;
    }

    public void setNomCommercialPrdt(String nomCommercialPrdt) {
        this.nomCommercialPrdt = nomCommercialPrdt;
    }

    public String getSubstanceActive() {
        return substanceActive;
    }

    public void setSubstanceActive(String substanceActive) {
        this.substanceActive = substanceActive;
    }

    public String getRenseignCompl() {
        return renseignCompl;
    }

    public void setRenseignCompl(String renseignCompl) {
        this.renseignCompl = renseignCompl;
    }

    public String getDeclSuppl() {
        return declSuppl;
    }

    public void setDeclSuppl(String declSuppl) {
        this.declSuppl = declSuppl;
    }

    public String getNomInspecteur() {
        return nomInspecteur;
    }

    public void setNomInspecteur(String nomInspecteur) {
        this.nomInspecteur = nomInspecteur;
    }

    public String getNumCamion() {
        return numCamion;
    }

    public void setNumCamion(String numCamion) {
        this.numCamion = numCamion;
    }

    public String getMagasinProv() {
        return magasinProv;
    }

    public void setMagasinProv(String magasinProv) {
        this.magasinProv = magasinProv;
    }

    public String getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(String dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    public String getAffreteur() {
        return affreteur;
    }

    public void setAffreteur(String affreteur) {
        this.affreteur = affreteur;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateInspection() {
        return dateInspection;
    }

    public void setDateInspection(Date dateInspection) {
        this.dateInspection = dateInspection;
    }

    public Date getDatePrevueInspection() {
        return datePrevueInspection;
    }

    public void setDatePrevueInspection(Date datePrevueInspection) {
        this.datePrevueInspection = datePrevueInspection;
    }

    public Date getDateDepartPreuve() {
        return dateDepartPreuve;
    }

    public void setDateDepartPreuve(Date dateDepartPreuve) {
        this.dateDepartPreuve = dateDepartPreuve;
    }

    public String getLieuInspection() {
        return lieuInspection;
    }

    public void setLieuInspection(String lieuInspection) {
        this.lieuInspection = lieuInspection;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getOfficierNavirePresent() {
        return officierNavirePresent;
    }

    public void setOfficierNavirePresent(String officierNavirePresent) {
        this.officierNavirePresent = officierNavirePresent;
    }

    public String getPartieNavireVisite() {
        return partieNavireVisite;
    }

    public void setPartieNavireVisite(String partieNavireVisite) {
        this.partieNavireVisite = partieNavireVisite;
    }

    public String getMesure() {
        return mesure;
    }

    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    public String getEmplacements() {
        return emplacements;
    }

    public void setEmplacements(String emplacements) {
        this.emplacements = emplacements;
    }

    public String getRemarquesParticulieres() {
        return remarquesParticulieres;
    }

    public void setRemarquesParticulieres(String remarquesParticulieres) {
        this.remarquesParticulieres = remarquesParticulieres;
    }

    public Navire getNavire() {
        return navire;
    }

    public void setNavire(Navire navire) {
        this.navire = navire;
    }

    public com.acl.iccd.core.shared.oldEntities.IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    public com.acl.iccd.core.shared.oldEntities.IccdFormaliteMinisterielle getFormalite() {
        return formalite;
    }

    public void setFormalite(IccdFormaliteMinisterielle formalite) {
        this.formalite = formalite;
    }

    public com.acl.iccd.core.shared.oldEntities.IccdTypeInspPhyto getIdTypeInspectPhyto() {
        return idTypeInspectPhyto;
    }

    public void setIdTypeInspectPhyto(IccdTypeInspPhyto idTypeInspectPhyto) {
        this.idTypeInspectPhyto = idTypeInspectPhyto;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public String getNomDemandeur() {
        return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.nomDemandeur = nomDemandeur;
    }

    public String getProfessionDemandeur() {
        return professionDemandeur;
    }

    public void setProfessionDemandeur(String professionDemandeur) {
        this.professionDemandeur = professionDemandeur;
    }

    public String getAdresseDemandeur() {
        return adresseDemandeur;
    }

    public void setAdresseDemandeur(String adresseDemandeur) {
        this.adresseDemandeur = adresseDemandeur;
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
        hash += (idMaInspection != null ? idMaInspection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMaPhytoSanitaire)) {
            return false;
        }
        IccdMaPhytoSanitaire other = (IccdMaPhytoSanitaire) object;
        if ((this.idMaInspection == null && other.idMaInspection != null) || (this.idMaInspection != null && !this.idMaInspection.equals(other.idMaInspection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMaPhytoSanitaire[ idMaInspection=" + idMaInspection + " ]";
    }
    
}
