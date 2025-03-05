/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.IccdProduit;
import com.acl.iccd.core.entities.IccdTypePv;
import com.acl.iccd.core.entities.Navire;

import java.io.Serializable;
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

/**
 *
 * @author Matthias
 */
@Entity
@Table(name = "ICCD_PROCES_VERBAL", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_PROCES_VERBAL",sequenceName = "S_ICCD_PROCES_VERBAL", allocationSize = 1)
public class IccdProcesverbal implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_PROCES_VERBAL")
    @Column(name = "ID_PV")
    private Long idPv;
    @Size(max = 254)
    @Column(name = "NUMERO")
    private String numero;
    
    @Column(name = "DATE_PV")
    @Temporal(TemporalType.DATE)
    private Date datePv;
    @Size(max = 254)
    @Column(name = "NOM_AGENT")
    private String nomAgent;
    @Size(max = 254)
    @Column(name = "PRENOM_AGENT")
    private String prenomAgent;
    @Size(max = 254)
    @Column(name = "POINT_ENTREE")
    private String pointEntree;
    
    @Column(name = "DATE_INSPECTION")
    @Temporal(TemporalType.DATE)
    private Date dateInspection;    
    
    @Column(name = "LIEU_INSPECTION")
    @Size(max = 254)
    private String lieuInspection;   
    
    @Size(max = 254)
    @Column(name = "NUMERO_FACTURE")
    private String numeroFacture;
    
    @Size(max = 254)
    @Column(name = "RESULTAT_INSPECTION")
    private String resultatInspection;
        
    @Size(max = 254)
    @Column(name = "NUM_CERTIFICAT_PHYTO")
    private String numCertifPhyto;

    @Column(name = "HEURE_DEBUT_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebutInspection;

    @Column(name = "HEURE_FIN_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureFinInspection;

    @Size(max = 254)
    @Column(name = "NOM_PRENOMS_EXPEDITEUR")
    private String nomPrenomsExpediteur;
    @Size(max = 254)
    @Column(name = "RAISON_SOCIALE_EXP")
    private String raisonSocialeExp;
    @Size(max = 254)
    @Column(name = "ADRESSE_EXPEDITEUR")
    private String adresseExpediteur;
    @Size(max = 254)
    @Column(name = "NOM_PRENOMS_DESTINATAIRE")
    private String nomPrenomsDestinataire;
    @Size(max = 254)
    @Column(name = "RAISON_SOCIALE_DESTINATAIRE")
    private String raisonsocialeDestinataire;
    @Size(max = 254)
    @Column(name = "ADRESSE_DESTINATAIRE")
    private String adresseDestinataire;
    @Size(max = 254)
    @Column(name = "NUM_ENREG_MOYTRANSP")
    private String numEnregMoytransp;

    @Column(name = "DATE_ARRIVEE_MOYTRANSP")
    @Temporal(TemporalType.DATE)
    private Date dateArriveeMoytransp;

    @Column(name = "QTE_POIDS_NET")
    private double qtePoidsNet;

    @Column(name = "QTE_POIDS_BRUT")
    private double qtePoidsBrut;

    @Size(max = 254)
    @Column(name = "TRAIT_SUBSACTIVE")
    private String traitSubsactive;
    
    @Size(max = 254)
    @Column(name = "CHEF_POSTE")
    private String chefPoste;

    @Size(max = 254)
    @Column(name = "VOL")
    private String vol;
    
    @Size(max = 254)
    @Column(name = "TRAIT_CONCENTRATION")
    private String traitConcentration;

    @Column(name = "TRAIT_PDT_TEMPERATURE")
    private Double traitPdtTemperature;

    @Column(name = "TRAIT_PRDT_DUREE")
    private int traitPrdtDuree;

    @Size(max = 254)
    @Column(name = "NOM_NAVIRE")
    private String nomNavire;

    @Size(max = 254)
    @Column(name = "AFFRETEUR")
    private String affreteur;

    @Size(max = 254)
    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "DATE_DEPART_PREVUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepartPrevue;

    @Size(max = 254)
    @Column(name = "PARTIE_NAVIRE_VIS")
    private String partieNavireVis;

    @Size(max = 254)
    @Column(name = "EMPLACEMENT")
    private String emplacement;

    @Size(max = 254)
    @Column(name = "MESURE")
    private String mesure;

    @Size(max = 254)
    @Column(name = "NUM_CAMION")
    private String numCamion;

    @Size(max = 254)
    @Column(name = "REMARQUES_PARTI")
    private String remarquesParti;

    @Column(name = "DATE_DEL_CERTPHYTO")
    @Temporal(TemporalType.DATE)
    private Date dateDelCertphyto;
    
    @Size(max = 254)
    @Column(name = "NOM_COMMANDANT")
    private String nomCommandant;
    
    @Size(max = 254)
    @Column(name = "OFFICIER_NAVIRE_PRESENT")
    private String officierNavirePresent;
    
    @Size(max = 254)
    @Column(name = "VIA")
    private String via;
        
    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;
    
    @Column(name = "DATE_APPLICATION")
    @Temporal(TemporalType.DATE)
    private Date dateApplication;
   
    @Column(name = "CONFORMITE_DU_CERT")
    private BigInteger conformiteDuCert;

    @Size(max = 254)
    @Column(name = "NUM_PERMIS_IMPORT")
    private String numPermisImport;

    @Column(name = "DATE_DEL_PERMIS_IMP")
    @Temporal(TemporalType.DATE)
    private Date dateDelPermisImp;

    @Column(name = "CONFORMITE_PERMIS_IMP")
    private short conformitePermisImp;
    
    @Size(max = 254)
    @Column(name = "AUTRES_CERTIF")
    private String autresCertif;
    
    @Size(max = 254)
    @Column(name = "NUM_PERMIS_EXP")
    private String numPermisExp;
    
    @Column(name = "DATE_DEL_PERMIS_EXPORT")
    @Temporal(TemporalType.DATE)
    private Date dateDelPermisExport;
    
    @Column(name = "CONFORMITE_PERMIS_EXPORT")
    private short conformitePermisExport;
    
    @Size(max = 254)
    @Column(name = "CONTROLE_TECH")
    private String controleTech;
    
    @Column(name = "DEMANDEUR_PRESENT")
    private boolean demandeurPresent;

    @Column(name = "CONSIGNATAIRE_PRESENT")
    private boolean consignatairePresent;

    @Column(name = "ASSUREUR_PRESENT")
    private boolean assureurPresent;

    @Column(name = "AGENT_FORCE_ORDRE")
    private boolean agentForceOrdrePresent;

    @Column(name = "SRV_INSP_PRESENT")
    private boolean serviceInspectionPresent;

    @Column(name = "AGT_DOUANE_PRESENT")
    private boolean douanePresent;

    @Size(max = 254)
    @Column(name = "DECLARATION_SUPLEMENTAIRE")
    private String declarationSuplementaire;
    
    @Size(max = 254)
    @Column(name = "PRODUITS_PHARMACEUTIQUES")
    private String produitsPharmaceutiques;
    
    @Size(max = 254)
    @Column(name = "NOM_COMMERCIAL")
    private String nomCommercial;
    
    @Size(max = 254)
    @Column(name = "LIEU_POSTE")
    private String lieuPoste;
    
    @Size(max = 254)
    @Column(name = "CONTENEUR")
    private String conteneur;

    @JoinColumn(name = "ID_TYPE_PV", referencedColumnName = "ID_TYPE_PV")
    @ManyToOne
    private IccdTypePv idTypePv;
    
    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    @ManyToOne
    private Navire navire;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle idFm;

    @JoinColumn(name = "ID_PROCES_VERBAL", referencedColumnName = "ID_PV")
    @ManyToOne
    private com.acl.iccd.core.entities.IccdProcesverbal iccdProcesVerbal;

    /*@JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID_PRODUIT")
    @ManyToOne
    private IccdProduit iccdProduit;*/

    public IccdProcesverbal() {
    }

    public String getDeclarationSuplementaire() {
        return declarationSuplementaire;
    }

    public void setDeclarationSuplementaire(String declarationSuplementaire) {
        this.declarationSuplementaire = declarationSuplementaire;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    /**
     * @return the idPv
     */
    public Long getIdPv() {
        return idPv;
    }

    /**
     * @param idPv the idPv to set
     */
    public void setIdPv(Long idPv) {
        this.idPv = idPv;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    /**
     * @return the datePv
     */
    public Date getDatePv() {
        return datePv;
    }

    /**
     * @param datePv the datePv to set
     */
    public void setDatePv(Date datePv) {
        this.datePv = datePv;
    }

    /**
     * @return the nomAgent
     */
    public String getNomAgent() {
        return nomAgent;
    }

    /**
     * @param nomAgent the nomAgent to set
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    /**
     * @return the prenomAgent
     */
    public String getPrenomAgent() {
        return prenomAgent;
    }

    /**
     * @param prenomAgent the prenomAgent to set
     */
    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }

    /**
     * @return the pointEntree
     */
    public String getPointEntree() {
        return pointEntree;
    }

    public String getResultatInspection() {
        return resultatInspection;
    }

    public void setResultatInspection(String resultatInspection) {
        this.resultatInspection = resultatInspection;
    }

    public String getNumCamion() {
        return numCamion;
    }

    public void setNumCamion(String numCamion) {
        this.numCamion = numCamion;
    }

    /**
     * @param pointEntree the pointEntree to set
     */
    public void setPointEntree(String pointEntree) {
        this.pointEntree = pointEntree;
    }

    public String getNumCertifPhyto() {
        return numCertifPhyto;
    }

    public void setNumCertifPhyto(String numCertifPhyto) {
        this.numCertifPhyto = numCertifPhyto;
    }

    /**
     * @return the dateInspection
     */
    public Date getDateInspection() {
        return dateInspection;
    }

    /**
     * @param dateInspection the dateInspection to set
     */
    public void setDateInspection(Date dateInspection) {
        this.dateInspection = dateInspection;
    }

    /**
     * @return the heureDebutInspection
     */
    public Date getHeureDebutInspection() {
        return heureDebutInspection;
    }

    /**
     * @param heureDebutInspection the heureDebutInspection to set
     */
    public void setHeureDebutInspection(Date heureDebutInspection) {
        this.heureDebutInspection = heureDebutInspection;
    }

    public String getOfficierNavirePresent() {
        return officierNavirePresent;
    }

    public void setOfficierNavirePresent(String officierNavirePresent) {
        this.officierNavirePresent = officierNavirePresent;
    }
    
    /**
     * @return the heureFinInspection
     */
    public Date getHeureFinInspection() {
        return heureFinInspection;
    }

    /**
    * @return the chefPoste
    */
    public String getChefPoste() {
        return chefPoste;
    }

    /**
     * @param chefPoste the chefPoste to set
    */
    public void setChefPoste(String chefPoste) {
        this.chefPoste = chefPoste;
    }

    /**
     * @param heureFinInspection the heureFinInspection to set
    */
    public void setHeureFinInspection(Date heureFinInspection) {
        this.heureFinInspection = heureFinInspection;
    }

    /**
     * @return the nomPrenomsExpediteur
     */
    public String getNomPrenomsExpediteur() {
        return nomPrenomsExpediteur;
    }

    /**
     * @param nomPrenomsExpediteur the nomPrenomsExpediteur to set
     */
    public void setNomPrenomsExpediteur(String nomPrenomsExpediteur) {
        this.nomPrenomsExpediteur = nomPrenomsExpediteur;
    }

    public String getNomCommandant() {
        return nomCommandant;
    }

    public void setNomCommandant(String nomCommandant) {
        this.nomCommandant = nomCommandant;
    }

    public IccdFormaliteMinisterielle getIdFm() {
        return idFm;
    }

    public void setIdFm(IccdFormaliteMinisterielle idFm) {
        this.idFm = idFm;
    }

    public String getLieuInspection() {
        return lieuInspection;
    }

    public void setLieuInspection(String lieuInspection) {
        this.lieuInspection = lieuInspection;
    }

    /**
     * @return the raisonSocialeExp
     */
    public String getRaisonSocialeExp() {
        return raisonSocialeExp;
    }

    /**
     * @param raisonSocialeExp the raisonSocialeExp to set
     */
    public void setRaisonSocialeExp(String raisonSocialeExp) {
        this.raisonSocialeExp = raisonSocialeExp;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
    
    /**
     * @return the adresseExpediteur
     */
    public String getAdresseExpediteur() {
        return adresseExpediteur;
    }

    /**
     * @param adresseExpediteur the adresseExpediteur to set
     */
    public void setAdresseExpediteur(String adresseExpediteur) {
        this.adresseExpediteur = adresseExpediteur;
    }

    /**
     * @return the nomPrenomsDestinataire
     */
    public String getNomPrenomsDestinataire() {
        return nomPrenomsDestinataire;
    }

    /**
     * @param nomPrenomsDestinataire the nomPrenomsDestinataire to set
     */
    public void setNomPrenomsDestinataire(String nomPrenomsDestinataire) {
        this.nomPrenomsDestinataire = nomPrenomsDestinataire;
    }

    /**
     * @return the raisonsocialeDestinataire
     */
    public String getRaisonsocialeDestinataire() {
        return raisonsocialeDestinataire;
    }

    /**
     * @param raisonsocialeDestinataire the raisonsocialeDestinataire to set
     */
    public void setRaisonsocialeDestinataire(String raisonsocialeDestinataire) {
        this.raisonsocialeDestinataire = raisonsocialeDestinataire;
    }

    /**
     * @return the adresseDestinataire
     */
    public String getAdresseDestinataire() {
        return adresseDestinataire;
    }

    /**
     * @param adresseDestinataire the adresseDestinataire to set
     */
    public void setAdresseDestinataire(String adresseDestinataire) {
        this.adresseDestinataire = adresseDestinataire;
    }

    /**
     * @return the numEnregMoytransp
     */
    public String getNumEnregMoytransp() {
        return numEnregMoytransp;
    }

    /**
     * @param numEnregMoytransp the numEnregMoytransp to set
     */
    public void setNumEnregMoytransp(String numEnregMoytransp) {
        this.numEnregMoytransp = numEnregMoytransp;
    }

    /**
     * @return the dateArriveeMoytransp
     */
    public Date getDateArriveeMoytransp() {
        return dateArriveeMoytransp;
    }

    /**
     * @param dateArriveeMoytransp the dateArriveeMoytransp to set
     */
    public void setDateArriveeMoytransp(Date dateArriveeMoytransp) {
        this.dateArriveeMoytransp = dateArriveeMoytransp;
    }

    /**
     * @return the qtePoidsNet
     */
    public double getQtePoidsNet() {
        return qtePoidsNet;
    }

    /**
     * @param qtePoidsNet the qtePoidsNet to set
     */
    public void setQtePoidsNet(double qtePoidsNet) {
        this.qtePoidsNet = qtePoidsNet;
    }

    /**
     * @return the qtePoidsBrut
     */
    public double getQtePoidsBrut() {
        return qtePoidsBrut;
    }

    /**
     * @param qtePoidsBrut the qtePoidsBrut to set
     */
    public void setQtePoidsBrut(double qtePoidsBrut) {
        this.qtePoidsBrut = qtePoidsBrut;
    }

    /**
     * @return the traitSubsactive
     */
    public String getTraitSubsactive() {
        return traitSubsactive;
    }

    /**
     * @param traitSubsactive the traitSubsactive to set
     */
    public void setTraitSubsactive(String traitSubsactive) {
        this.traitSubsactive = traitSubsactive;
    }

    /**
     * @return the traitConcentration
     */
    public String getTraitConcentration() {
        return traitConcentration;
    }

    /**
     * @param traitConcentration the traitConcentration to set
     */
    public void setTraitConcentration(String traitConcentration) {
        this.traitConcentration = traitConcentration;
    }

    /**
     * @return the traitPdtTemperature
     */
    public Double getTraitPdtTemperature() {
        return traitPdtTemperature;
    }

    /**
     * @param traitPdtTemperature the traitPdtTemperature to set
     */
    public void setTraitPdtTemperature(Double traitPdtTemperature) {
        this.traitPdtTemperature = traitPdtTemperature;
    }

    /**
     * @return the traitPrdtDuree
     */
    public int getTraitPrdtDuree() {
        return traitPrdtDuree;
    }

    /**
     * @param traitPrdtDuree the traitPrdtDuree to set
     */
    public void setTraitPrdtDuree(int traitPrdtDuree) {
        this.traitPrdtDuree = traitPrdtDuree;
    }

    /**
     * @return the nomNavire
     */
    public String getNomNavire() {
        return nomNavire;
    }

    /**
     * @param nomNavire the nomNavire to set
     */
    public void setNomNavire(String nomNavire) {
        this.nomNavire = nomNavire;
    }

    /**
     * @return the affreteur
     */
    public String getAffreteur() {
        return affreteur;
    }

    /**
     * @param affreteur the affreteur to set
     */
    public void setAffreteur(String affreteur) {
        this.affreteur = affreteur;
    }

    /**
     * @return the provenance
     */
    public String getProvenance() {
        return provenance;
    }

    /**
     * @param provenance the provenance to set
     */
    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    /**
     * @return the dateDepartPrevue
     */
    public Date getDateDepartPrevue() {
        return dateDepartPrevue;
    }

    /**
     * @param dateDepartPrevue the dateDepartPrevue to set
     */
    public void setDateDepartPrevue(Date dateDepartPrevue) {
        this.dateDepartPrevue = dateDepartPrevue;
    }

    
    /**
     * @return the dateTraitement to set
     */
    public Date getDateTraitement() {
        return dateTraitement;
    }

    /**
     * @param dateTraitement the dateTraitement to set
     */
    public void setDateTraitement(Date dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    /**
     * @return the dateApplication to set
     */
    public Date getDateApplication() {
        return dateApplication;
    }

    /**
     * @param dateApplication the dateApplication to set
     */
    public void setDateApplication(Date dateApplication) {
        this.dateApplication = dateApplication;
    }

    /**
     * @return the partieNavireVis
     */
    public String getPartieNavireVis() {
        return partieNavireVis;
    }

    /**
     * @param partieNavireVis the partieNavireVis to set
     */
    public void setPartieNavireVis(String partieNavireVis) {
        this.partieNavireVis = partieNavireVis;
    }

    /**
     * @return the emplacement
     */
    public String getEmplacement() {
        return emplacement;
    }

    /**
     * @param emplacement the emplacement to set
     */
    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    /**
     * @return the mesure
     */
    public String getMesure() {
        return mesure;
    }

    /**
     * @param mesure the mesure to set
     */
    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    /**
     * @return the remarquesParti
     */
    public String getRemarquesParti() {
        return remarquesParti;
    }

    /**
     * @param remarquesParti the remarquesParti to set
     */
    public void setRemarquesParti(String remarquesParti) {
        this.remarquesParti = remarquesParti;
    }

    /**
     * @return the dateDelCertphyto
     */
    public Date getDateDelCertphyto() {
        return dateDelCertphyto;
    }

    /**
     * @param dateDelCertphyto the dateDelCertphyto to set
     */
    public void setDateDelCertphyto(Date dateDelCertphyto) {
        this.dateDelCertphyto = dateDelCertphyto;
    }

    /**
     * @return the conformiteDuCert
     */
    public BigInteger getConformiteDuCert() {
        return conformiteDuCert;
    }

    /**
     * @param conformiteDuCert the conformiteDuCert to set
     */
    public void setConformiteDuCert(BigInteger conformiteDuCert) {
        this.conformiteDuCert = conformiteDuCert;
    }

    /**
     * @return the numPermisImport
     */
    public String getNumPermisImport() {
        return numPermisImport;
    }

    /**
     * @param numPermisImport the numPermisImport to set
     */
    public void setNumPermisImport(String numPermisImport) {
        this.numPermisImport = numPermisImport;
    }

    /**
     * @return the dateDelPermisImp
     */
    public Date getDateDelPermisImp() {
        return dateDelPermisImp;
    }

    /**
     * @param dateDelPermisImp the dateDelPermisImp to set
     */
    public void setDateDelPermisImp(Date dateDelPermisImp) {
        this.dateDelPermisImp = dateDelPermisImp;
    }

    /**
     * @return the conformitePermisImp
     */
    public short getConformitePermisImp() {
        return conformitePermisImp;
    }

    /**
     * @param conformitePermisImp the conformitePermisImp to set
     */
    public void setConformitePermisImp(short conformitePermisImp) {
        this.conformitePermisImp = conformitePermisImp;
    }

    /**
     * @return the autresCertif
     */
    public String getAutresCertif() {
        return autresCertif;
    }

    /**
     * @param autresCertif the autresCertif to set
     */
    public void setAutresCertif(String autresCertif) {
        this.autresCertif = autresCertif;
    }

    /**
     * @return the numPermisExp
     */
    public String getNumPermisExp() {
        return numPermisExp;
    }

    /**
     * @param numPermisExp the numPermisExp to set
     */
    public void setNumPermisExp(String numPermisExp) {
        this.numPermisExp = numPermisExp;
    }

    /**
     * @return the dateDelPermisExport
     */
    public Date getDateDelPermisExport() {
        return dateDelPermisExport;
    }

    /**
     * @param dateDelPermisExport the dateDelPermisExport to set
     */
    public void setDateDelPermisExport(Date dateDelPermisExport) {
        this.dateDelPermisExport = dateDelPermisExport;
    }

    /**
     * @return the conformitePermisExport
     */
    public short getConformitePermisExport() {
        return conformitePermisExport;
    }

    /**
     * @param conformitePermisExport the conformitePermisExport to set
     */
    public void setConformitePermisExport(short conformitePermisExport) {
        this.conformitePermisExport = conformitePermisExport;
    }

    /**
     * @return the controleTech
     */
    public String getControleTech() {
        return controleTech;
    }

    /**
     * @param controleTech the controleTech to set
     */
    public void setControleTech(String controleTech) {
        this.controleTech = controleTech;
    }

    /**
     * @return the idTypePv
     */
    public IccdTypePv getIdTypePv() {
        return idTypePv;
    }

    /**
     * @param idTypePv the idTypePv to set
     */
    public void setIdTypePv(IccdTypePv idTypePv) {
        this.idTypePv = idTypePv;
    }

    /**
     * @return the navire
     */
    public Navire getNavire() {
        return navire;
    }

    /**
     * @param navire the navire to set
     */
    public void setNavire(Navire navire) {
        this.navire = navire;
    }

    public boolean isDemandeurPresent() {
        return demandeurPresent;
    }
    public String getDemandeurPresent() {
      return demandeurPresent ? "Oui": "Non";
    }

    public void setDemandeurPresent(boolean demandeurPresent) {
        this.demandeurPresent = demandeurPresent;
    }

    public boolean isConsignatairePresent() {
        return consignatairePresent;
    }

    public void setConsignatairePresent(boolean consignatairePresent) {
        this.consignatairePresent = consignatairePresent;
    }

    public IccdProduit getIccdProduit() {
        return iccdProduit;
    }

    public void setIccdProduit(IccdProduit iccdProduit) {
        this.iccdProduit = iccdProduit;
    }

    public boolean isAssureurPresent() {
        return assureurPresent;
    }

    public void setAssureurPresent(boolean assureurPresent) {
        this.assureurPresent = assureurPresent;
    }

    public boolean isAgentForceOrdrePresent() {
        return agentForceOrdrePresent;
    }

    public void setAgentForceOrdrePresent(boolean agentForceOrdrePresent) {
        this.agentForceOrdrePresent = agentForceOrdrePresent;
    }

    public boolean isServiceInspectionPresent() {
        return serviceInspectionPresent;
    }

    public void setServiceInspectionPresent(boolean serviceInspectionPresent) {
        this.serviceInspectionPresent = serviceInspectionPresent;
    }

    public boolean isDouanePresent() {
        return douanePresent;
    }

    public void setDouanePresent(boolean douanePresent) {
        this.douanePresent = douanePresent;
    }

    public String getProduitsPharmaceutiques() {
        return produitsPharmaceutiques;
    }

    public void setProduitsPharmaceutiques(String produitsPharmaceutiques) {
        this.produitsPharmaceutiques = produitsPharmaceutiques;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public String getLieuPoste() {
        return lieuPoste;
    }

    public void setLieuPoste(String lieuPoste) {
        this.lieuPoste = lieuPoste;
    }

    public String getConteneur() {
        return conteneur;
    }

    public void setConteneur(String conteneur) {
        this.conteneur = conteneur;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdPv() != null ? getIdPv().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdProcesverbal)) {
            return false;
        }
        IccdProcesverbal other = (IccdProcesverbal) object;
        if ((this.getIdPv() == null && other.getIdPv() != null) || (this.getIdPv() != null && !this.idPv.equals(other.idPv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdProcesverbal[ idPv=" + getIdPv() + " ]";
    }
    
}
