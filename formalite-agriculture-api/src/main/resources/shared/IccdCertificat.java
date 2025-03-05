/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.Navire;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_CERTIFICAT", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_CERTIFICAT",sequenceName = "S_ICCD_CERTIFICAT", allocationSize = 1)
@XmlRootElement
public class IccdCertificat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_CERTIFICAT")
    @Column(name = "ID_CERTIFICAT")
    private BigDecimal idCertificat;

    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;

    @Size(max = 254)
    @Column(name = "ORIGINE_PRDT")
    private String origineprdt;

    @Size(max = 254)
    @Column(name = "EXPEDITEUR")
    private String expediteur;
    @Column(name = "POIDS_TOTAL")
    private BigInteger poidstotal;
    @Column(name = "NOMBRE")
    private BigInteger nombre;
    @Size(max = 254)
    @Column(name = "RACE")
    private String race;
    @Size(max = 254)
    @Column(name = "ESPECES")
    private String especes;
    @Size(max = 254)
    @Column(name = "CONDITIONNEMENT")
    private String conditionnement;
    @Column(name = "TRAITEMENT")
    private String traitement;
    @Size(max = 254)
    @Column(name = "IDENTIFICATION")
    private String identification;
    @Size(max = 254)
    @Column(name = "NATURE_EMBALLAGE")
    private String natureemballage;
    @Column(name = "DATE_SIGNATURE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesignature;
    @Column(name = "DATE_DEPART")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedepart;
    @Size(max = 254)
    @Column(name = "SIGNATURE")
    private String signature;
    @Column(name = "MOYEN_TRANSPORT")
    private String moyentransport;
    @Size(max = 254)
    @Column(name = "NOM_EXPEDITEUR")
    private String nomexpediteur;
    @Size(max = 254)
    @Column(name = "ADRESSE_EXPE")
    private String adresseexpe;
    @Size(max = 254)
    @Column(name = "TYPE_IMPORTATION")
    private String typeimportation;
    @Size(max = 254)
    @Column(name = "NOM_DESTINAT")
    private String nomdestinat;
    @Size(max = 254)
    @Column(name = "ADRESSE_DESTINAT")
    private String adressedestinat;
    @Size(max = 254)
    @Column(name = "TYPE_CERTIFICAT")
    private String typecertificat;
    @Size(max = 254)
    @Column(name = "PAYS_EXPEDITEUR")
    private String paysexpediteur;
    @Size(max = 254)
    @Column(name = "PAYS_ORIGINE")
    private String paysorigine;
    @Size(max = 254)
    @Column(name = "AUTORITE_COMPETENTE")
    private String autoritecompetente;
    @Size(max = 254)
    @Column(name = "POSTE_FRONTALIER_PREVU")
    private String postefrontalierprevu;
    @Size(max = 254)
    @Column(name = "VETERINAIRE")
    private String veterinaire;
    @Size(max = 254)
    @Column(name = "PAYS_DESTINATION")
    private String paysdestination;
    @Column(name = "LIEU_CHARGEMENT")
    private String lieuchargement;
    @Column(name = "AUTRE")
    private String autre;
    @Column(name = "DESCRIPTION_MARCHANDISE")
    private String descriptionmarchandise;
    @Column(name = "SEXE")
    private String sexe;
    @Size(max = 254)
    @Column(name = "NOM_NAVIRE")
    private String nomnavire;
    @Column(name = "AGE")
    private String age;
    @Column(name = "QUANTITE")
    private BigInteger quantite;
    @Column(name = "NATURE_PRODUIT")
    private String natureproduit;
    @Size(max = 254)
    @Column(name = "SOCIETE_EMPOTAGE")
    private String societeEmpotage;
    @Size(max = 254)
    @Column(name = "LIEU_EMPOTAGE")
    private String lieuEmpotage;
    @Size(max = 254)
    @Column(name = "TRANSITAIRE")
    private String transitaire;
    @Size(max = 254)
    @Column(name = "NOM_FOURNISSEUR")
    private String nomFournisseur;
    @Size(max = 254)
    @Column(name = "REF_AUTORISATION_EXPLOITATION")
    private String refAutorisationExploitation;
    @Size(max = 254)
    @Column(name = "REF_AUTORISATION_TRANSPORT")
    private String refAutorisationTransport;
    @Size(max = 254)
    @Column(name = "PERMI_CIRCULATION")
    private String permiCirculation;

    @Size(max = 254)
    @Column(name = "EMBALLEUR")
    private String emballeur;

    @Column(name = "NUMPV")
    private long numPv;

    @Size(max = 254)
    @Column(name = "OBSERVATION")
    private String observation;


    @Size(max = 254)
    @Column(name = "NOM_SCIENTIFIQUE")
    private String nomScientifique;

    @Size(max = 254)
    @Column(name = "TEMPERATURE")
    private String temperature;

    @Size(max = 254)
    @Column(name = "NOM_PRENOM_DEPOSITAIRE")
    private String nomPrenomDepositaire;

    @Column(name = "ACCORDER")
    private int accorder;

    @Size(max = 254)
    @Column(name = "NUM_QUITANCE")
    private String numQuitance;

    @Size(max = 254)
    @Column(name = "AGENT_FORESTIER")
    private String agentForestier;

    @Size(max = 254)
    @Column(name = "COMPTABLE_REGISSEUR")
    private String comptableRegisseur;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_QUITANCE")
    private Date dateQuitance;

    @Size(max = 254)
    @Column(name = "NUM_DRERF")
    private String numDRERF;

    @Size(max = 254)
    @Column(name = "POINT_ENTREE")
    private String pointEntree;

    @Size(max = 254)
    @Column(name = "MOTIF_REFUS")
    private String motifRefus;

    @ManyToOne
    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    private Navire navire;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle idFm;
    
    @JoinColumn(name = "ID_TYPE_CERTIFICAT", referencedColumnName = "ID_TYPE_CERTIFICAT")
    @ManyToOne
    private IccdTypeCertificat idtypecertificat;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private IccdProduit iccdProduit;

    public long getNumPv() {
        return numPv;
    }

    public void setNumPv(long numPv) {
        this.numPv = numPv;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public IccdCertificat() {
    }


    public String getNumGenere() {
        return numGenere;
    }

    public void setNumGenere(String numGenere) {
        this.numGenere = numGenere;
    }

    public String getNomScientifique() {
        return nomScientifique;
    }

    public void setNomScientifique(String nomScientifique) {
        this.nomScientifique = nomScientifique;
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

    public String getNumDRERF() {
        return numDRERF;
    }

    public void setNumDRERF(String numDRERF) {
        this.numDRERF = numDRERF;
    }

    public String getPaysexpediteur() {
        return paysexpediteur;
    }

    public void setPaysexpediteur(String paysexpediteur) {
        this.paysexpediteur = paysexpediteur;
    }

    public String getAutoritecompetente() {
        return autoritecompetente;
    }

    public void setAutoritecompetente(String autoritecompetente) {
        this.autoritecompetente = autoritecompetente;
    }

    public String getPaysorigine() {
        return paysorigine;
    }

    public BigInteger getQuantite() {
        return quantite;
    }

    public void setQuantite(BigInteger quantite) {
        this.quantite = quantite;
    }

    public void setPaysorigine(String paysorigine) {
        this.paysorigine = paysorigine;
    }

    public String getAge() {
        return age;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getSocieteEmpotage() {
        return societeEmpotage;
    }

    public void setSocieteEmpotage(String societeEmpotage) {
        this.societeEmpotage = societeEmpotage;
    }

    public String getLieuEmpotage() {
        return lieuEmpotage;
    }

    public void setLieuEmpotage(String lieuEmpotage) {
        this.lieuEmpotage = lieuEmpotage;
    }

    public String getTransitaire() {
        return transitaire;
    }

    public void setTransitaire(String transitaire) {
        this.transitaire = transitaire;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getRefAutorisationExploitation() {
        return refAutorisationExploitation;
    }

    public void setRefAutorisationExploitation(String refAutorisationExploitation) {
        this.refAutorisationExploitation = refAutorisationExploitation;
    }

    public String getRefAutorisationTransport() {
        return refAutorisationTransport;
    }

    public void setRefAutorisationTransport(String refAutorisationTransport) {
        this.refAutorisationTransport = refAutorisationTransport;
    }

    public String getPermiCirculation() {
        return permiCirculation;
    }

    public void setPermiCirculation(String permiCirculation) {
        this.permiCirculation = permiCirculation;
    }

    public String getNomPrenomDepositaire() {
        return nomPrenomDepositaire;
    }

    public void setNomPrenomDepositaire(String nomPrenomDepositaire) {
        this.nomPrenomDepositaire = nomPrenomDepositaire;
    }

    public String getPointEntree() {
        return pointEntree;
    }

    public void setPointEntree(String pointEntree) {
        this.pointEntree = pointEntree;
    }

    public String getNumQuitance() {
        return numQuitance;
    }

    public void setNumQuitance(String numQuitance) {
        this.numQuitance = numQuitance;
    }

    public String getAgentForestier() {
        return agentForestier;
    }

    public void setAgentForestier(String agentForestier) {
        this.agentForestier = agentForestier;
    }

    public String getComptableRegisseur() {
        return comptableRegisseur;
    }

    public void setComptableRegisseur(String comptableRegisseur) {
        this.comptableRegisseur = comptableRegisseur;
    }

    public Date getDateQuitance() {
        return dateQuitance;
    }

    public void setDateQuitance(Date dateQuitance) {
        this.dateQuitance = dateQuitance;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPostefrontalierprevu() {
        return postefrontalierprevu;
    }

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public String getDescriptionmarchandise() {
        return descriptionmarchandise;
    }

    public void setDescriptionmarchandise(String descriptionmarchandise) {
        this.descriptionmarchandise = descriptionmarchandise;
    }

    public String getEmballeur() {
        return emballeur;
    }

    public void setEmballeur(String emballeur) {
        this.emballeur = emballeur;
    }

    public String getNatureproduit() {
        return natureproduit;
    }

    public void setNatureproduit(String natureproduit) {
        this.natureproduit = natureproduit;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setPostefrontalierprevu(String postefrontalierprevu) {
        this.postefrontalierprevu = postefrontalierprevu;
    }

    public String getVeterinaire() {
        return veterinaire;
    }

    public String getPaysdestination() {
        return paysdestination;
    }

    public String getLieuchargement() {
        return lieuchargement;
    }

    public void setLieuchargement(String lieuchargement) {
        this.lieuchargement = lieuchargement;
    }

    public void setPaysdestination(String paysdestination) {
        this.paysdestination = paysdestination;
    }

    public void setVeterinaire(String veterinaire) {
        this.veterinaire = veterinaire;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

    public IccdCertificat(BigDecimal idCertificat) {
        this.idCertificat = idCertificat;
    }

    public BigDecimal getIdCertificat() {
        return idCertificat;
    }

    public void setIdCertificat(BigDecimal idCertificat) {
        this.idCertificat = idCertificat;
    }

    public String getOrigineprdt() {
        return origineprdt;
    }

    public void setOrigineprdt(String origineprdt) {
        this.origineprdt = origineprdt;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public BigInteger getPoidstotal() {
        return poidstotal;
    }

    public void setPoidstotal(BigInteger poidstotal) {
        this.poidstotal = poidstotal;
    }

    public BigInteger getNombre() {
        return nombre;
    }

    public void setNombre(BigInteger nombre) {
        this.nombre = nombre;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEspeces() {
        return especes;
    }

    public void setEspeces(String especes) {
        this.especes = especes;
    }

    public String getConditionnement() {
        return conditionnement;
    }

    public void setConditionnement(String conditionnement) {
        this.conditionnement = conditionnement;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getNatureemballage() {
        return natureemballage;
    }

    public void setNatureemballage(String natureemballage) {
        this.natureemballage = natureemballage;
    }

    public Date getDatesignature() {
        return datesignature;
    }

    public void setDatesignature(Date datesignature) {
        this.datesignature = datesignature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMoyentransport() {
        return moyentransport;
    }

    public void setMoyentransport(String moyentransport) {
        this.moyentransport = moyentransport;
    }

    public String getNomexpediteur() {
        return nomexpediteur;
    }

    public void setNomexpediteur(String nomexpediteur) {
        this.nomexpediteur = nomexpediteur;
    }

    public String getAdresseexpe() {
        return adresseexpe;
    }

    public void setAdresseexpe(String adresseexpe) {
        this.adresseexpe = adresseexpe;
    }

    public String getTypeimportation() {
        return typeimportation;
    }

    public void setTypeimportation(String typeimportation) {
        this.typeimportation = typeimportation;
    }

    public String getNomdestinat() {
        return nomdestinat;
    }

    public void setNomdestinat(String nomdestinat) {
        this.nomdestinat = nomdestinat;
    }

    public String getAdressedestinat() {
        return adressedestinat;
    }

    public void setAdressedestinat(String adressedestinat) {
        this.adressedestinat = adressedestinat;
    }

    public String getTypecertificat() {
        return typecertificat;
    }

    public void setTypecertificat(String typecertificat) {
        this.typecertificat = typecertificat;
    }

    public String getNomnavire() {
        return nomnavire;
    }

    public void setNomnavire(String nomnavire) {
        this.nomnavire = nomnavire;
    }

    public IccdFormaliteMinisterielle getIdFm() {
        return idFm;
    }

    public void setIdFm(IccdFormaliteMinisterielle idFm) {
        this.idFm = idFm;
    }

    public IccdTypeCertificat getIdtypecertificat() {
        return idtypecertificat;
    }

    public void setIdtypecertificat(IccdTypeCertificat idtypecertificat) {
        this.idtypecertificat = idtypecertificat;
    }

    public Navire getNavire() {
        return navire;
    }

    public IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    public void setNavire(Navire navire) {
        this.navire = navire;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCertificat != null ? idCertificat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdCertificat)) {
            return false;
        }
        IccdCertificat other = (IccdCertificat) object;
        if ((this.idCertificat == null && other.idCertificat != null) || (this.idCertificat != null && !this.idCertificat.equals(other.idCertificat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdCertificat[ idCertificat=" + idCertificat + " ]";
    }
    
}
