/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;
import com.acl.iccd.core.entities.IccdMsDeclSanteRep;
import com.acl.iccd.core.entities.IccdMsListPort;
import com.acl.iccd.core.entities.Navire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;


/**
 *
 * @author pythagoras
 */
@Entity
@Table(name = "ICCD_MS_DECL_MARITIME_SANTE", catalog = "", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_MS_DECL_MARITIME_SANTE",sequenceName = "S_ICCD_MS_DECL_MARITIME_SANTE", allocationSize = 1)
public class IccdMsDecMaritimeSante implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_MS_DECL_MARITIME_SANTE")
    @Column(name = "ID_DEC_SANTE")
    private Long idDecSante;    
    
    @Column(name = "PORT_PRESENSATION")
    private String portPresensation;
    
    @Column(name = "DATE_DECL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDecl;
    
    @Size(max = 254)
    @Column(name = "NOM_NAVIRE")
    private String nomNavire;
    
    @Size(max = 254)
    @Column(name = "PROVENANCE_NAVIRE")
    private String provenanceNavire;
    
    @Size(max = 254)
    @Column(name = "DESTINATION_NAVIRE")
    private String destinationNavire;
    
    @Size(max = 254)
    @Column(name = "NATIONALITE")
    private String nationalite;
    
    @Size(max = 254)
    @Column(name = "NOM_COMMANDANT")
    private String nomCommandant;
    
    @Column(name = "TONNAGE_NET_REGISTRE")
    private Double tonnageNetRegistre;
    
    @Size(max = 254)
    @Column(name = "SSC_SSCEC")
    private String sscSscec;
    
    @Column(name = "DATE_CERTIFICAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCertificat;
    
    @Size(max = 254)
    @Column(name = "LIEU_EMISSION_CERTIF")
    private String lieuEmissionCertif;
    
    @Column(name = "NBRE_PASSAGE_CABINE")
    private int nbrePassageCabine;
    
    @Column(name = "NBRE_EQUIPAGE")
    private int nbreEquipage;
    
    @Column(name = "NBRE_PLATEFORME")
    private int nbrePlateforme;
    
    @Size(max = 254)
    @Column(name = "NUMERO_PASSE")
    private String numeroPasse;
    
    @Column(name = "LISTE_QUESTION_SANTE")
    private String listequestionsante;
    
    @Column(name = "DATE_SIGNATURE_COMMANDANT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSignatureCommandant;
    
    @Size(max = 254)
    @Column(name = "SIGNATURE_COMMANDANT")
    private String signatureCommandant;
    
    @Size(max = 254)
    @Column(name = "NOM_MEDECIN")
    private String nomMedecin;
    
    @Column(name = "DATE_SIGNATURE_MEDECIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datesignatureMedecin;
    
    @Size(max = 254)
    @Column(name = "SIGNATURE_MEDECIN")
    private String signatureMedecin;
    
    @Size(max = 254)
    @Column(name = "FICHE_CERTIFICAT_EXEMP")
    private String ficheCertificatExemp;
    
    @Size(max = 254)
    @Column(name = "FICHIER_JOINTCAL_DECL")
    private String fichierJointcalDecl;
    
    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle formaliteMinisterielle;
    
    @JoinColumn(name = "ID_NAVIRE", referencedColumnName = "ID_NAVIRE")
    @ManyToOne
    private Navire navire;
    
    @Transient
    private List<com.acl.iccd.core.entities.IccdMsDeclSanteRep> respList = new ArrayList<>();
    @Transient        
    private List<com.acl.iccd.core.entities.IccdMsListPort> portList  = new ArrayList<>() ;
    @Transient
    private String compteClient;
    
    public IccdMsDecMaritimeSante() {
        respList = new ArrayList();
        portList = new ArrayList();
    }

    public IccdMsDecMaritimeSante(Long idDecSante) {
        this.idDecSante = idDecSante;
    }

    public Long getIdDecSante() {
        return idDecSante;
    }

    public void setIdDecSante(Long idDecSante) {
        this.idDecSante = idDecSante;
    }

    public String getPortPresensation() {
        return portPresensation;
    }

    public void setPortPresensation(String portPresensation) {
        this.portPresensation = portPresensation;
    }

    
    /**
     * @return the dateDecl
     */
    public Date getDateDecl() {
        return dateDecl;
    }

    /**
     * @param dateDecl the dateDecl to set
     */
    public void setDateDecl(Date dateDecl) {
        this.dateDecl = dateDecl;
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
     * @return the provenanceNavire
     */
    public String getProvenanceNavire() {
        return provenanceNavire;
    }

    /**
     * @param provenanceNavire the provenanceNavire to set
     */
    public void setProvenanceNavire(String provenanceNavire) {
        this.provenanceNavire = provenanceNavire;
    }

    /**
     * @return the destinationNavire
     */
    public String getDestinationNavire() {
        return destinationNavire;
    }

    /**
     * @param destinationNavire the destinationNavire to set
     */
    public void setDestinationNavire(String destinationNavire) {
        this.destinationNavire = destinationNavire;
    }

    /**
     * @return the nationalite
     */
    public String getNationalite() {
        return nationalite;
    }

    /**
     * @param nationalite the nationalite to set
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    /**
     * @return the nomCommandant
     */
    public String getNomCommandant() {
        return nomCommandant;
    }

    /**
     * @param nomCommandant the nomCommandant to set
     */
    public void setNomCommandant(String nomCommandant) {
        this.nomCommandant = nomCommandant;
    }

    /**
     * @return the tonnageNetRegistre
     */
    public Double getTonnageNetRegistre() {
        return tonnageNetRegistre;
    }

    /**
     * @param tonnageNetRegistre the tonnageNetRegistre to set
     */
    public void setTonnageNetRegistre(Double tonnageNetRegistre) {
        this.tonnageNetRegistre = tonnageNetRegistre;
    }

    /**
     * @return the sscSscec
     */
    public String getSscSscec() {
        return sscSscec;
    }

    /**
     * @param sscSscec the sscSscec to set
     */
    public void setSscSscec(String sscSscec) {
        this.sscSscec = sscSscec;
    }

    /**
     * @return the dateCertificat
     */
    public Date getDateCertificat() {
        return dateCertificat;
    }

    /**
     * @param dateCertificat the dateCertificat to set
     */
    public void setDateCertificat(Date dateCertificat) {
        this.dateCertificat = dateCertificat;
    }

    /**
     * @return the lieuEmissionCertif
     */
    public String getLieuEmissionCertif() {
        return lieuEmissionCertif;
    }

    /**
     * @param lieuEmissionCertif the lieuEmissionCertif to set
     */
    public void setLieuEmissionCertif(String lieuEmissionCertif) {
        this.lieuEmissionCertif = lieuEmissionCertif;
    }

    /**
     * @return the nbrePassageCabine
     */
    public int getNbrePassageCabine() {
        return nbrePassageCabine;
    }

    /**
     * @param nbrePassageCabine the nbrePassageCabine to set
     */
    public void setNbrePassageCabine(int nbrePassageCabine) {
        this.nbrePassageCabine = nbrePassageCabine;
    }

    /**
     * @return the nbreEquipage
     */
    public int getNbreEquipage() {
        return nbreEquipage;
    }

    /**
     * @param nbreEquipage the nbreEquipage to set
     */
    public void setNbreEquipage(int nbreEquipage) {
        this.nbreEquipage = nbreEquipage;
    }

    /**
     * @return the nbrePlateforme
     */
    public int getNbrePlateforme() {
        return nbrePlateforme;
    }

    /**
     * @param nbrePlateforme the nbrePlateforme to set
     */
    public void setNbrePlateforme(int nbrePlateforme) {
        this.nbrePlateforme = nbrePlateforme;
    }

    /**
     * @return the numeroPasse
     */
    public String getNumeroPasse() {
        return numeroPasse;
    }

    /**
     * @param numeroPasse the numeroPasse to set
     */
    public void setNumeroPasse(String numeroPasse) {
        this.numeroPasse = numeroPasse;
    }

    /**
     * @return the listequestionsante
     */
    public String getListequestionsante() {
        return listequestionsante;
    }

    /**
     * @param listequestionsante the listequestionsante to set
     */
    public void setListequestionsante(String listequestionsante) {
        this.listequestionsante = listequestionsante;
    }

    /**
     * @return the dateSignatureCommandant
     */
    public Date getDateSignatureCommandant() {
        return dateSignatureCommandant;
    }

    /**
     * @param dateSignatureCommandant the dateSignatureCommandant to set
     */
    public void setDateSignatureCommandant(Date dateSignatureCommandant) {
        this.dateSignatureCommandant = dateSignatureCommandant;
    }

    /**
     * @return the signatureCommandant
     */
    public String getSignatureCommandant() {
        return signatureCommandant;
    }

    /**
     * @param signatureCommandant the signatureCommandant to set
     */
    public void setSignatureCommandant(String signatureCommandant) {
        this.signatureCommandant = signatureCommandant;
    }

    /**
     * @return the nomMedecin
     */
    public String getNomMedecin() {
        return nomMedecin;
    }

    /**
     * @param nomMedecin the nomMedecin to set
     */
    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    /**
     * @return the datesignatureMedecin
     */
    public Date getDatesignatureMedecin() {
        return datesignatureMedecin;
    }

    /**
     * @param datesignatureMedecin the datesignatureMedecin to set
     */
    public void setDatesignatureMedecin(Date datesignatureMedecin) {
        this.datesignatureMedecin = datesignatureMedecin;
    }

    /**
     * @return the signatureMedecin
     */
    public String getSignatureMedecin() {
        return signatureMedecin;
    }

    /**
     * @param signatureMedecin the signatureMedecin to set
     */
    public void setSignatureMedecin(String signatureMedecin) {
        this.signatureMedecin = signatureMedecin;
    }

    /**
     * @return the ficheCertificatExemp
     */
    public String getFicheCertificatExemp() {
        return ficheCertificatExemp;
    }

    /**
     * @param ficheCertificatExemp the ficheCertificatExemp to set
     */
    public void setFicheCertificatExemp(String ficheCertificatExemp) {
        this.ficheCertificatExemp = ficheCertificatExemp;
    }

    /**
     * @return the fichierJointcalDecl
     */
    public String getFichierJointcalDecl() {
        return fichierJointcalDecl;
    }

    /**
     * @param fichierJointcalDecl the fichierJointcalDecl to set
     */
    public void setFichierJointcalDecl(String fichierJointcalDecl) {
        this.fichierJointcalDecl = fichierJointcalDecl;
    }

    /**
     * @return the formaliteMinisterielle
     */
    public IccdFormaliteMinisterielle getFormaliteMinisterielle() {
        return formaliteMinisterielle;
    }

    /**
     * @param formaliteMinisterielle the formaliteMinisterielle to set
     */
    public void setFormaliteMinisterielle(IccdFormaliteMinisterielle formaliteMinisterielle) {
        this.formaliteMinisterielle = formaliteMinisterielle;
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

    /**
     * @return the respList
     */
    public List<com.acl.iccd.core.entities.IccdMsDeclSanteRep> getRespList() {
        return respList;
    }

    /**
     * @param respList the respList to set
     */
    public void setRespList(List<IccdMsDeclSanteRep> respList) {
        this.respList = respList;
    }

    /**
     * @return the portList
     */
    public List<com.acl.iccd.core.entities.IccdMsListPort> getPortList() {
        return portList;
    }

    /**
     * @param portList the portList to set
     */
    public void setPortList(List<IccdMsListPort> portList) {
        this.portList = portList;
    }

    /**
     * @return the compteClient
     */
    public String getCompteClient() {
        return compteClient;
    }

    /**
     * @param compteClient the compteClient to set
     */
    public void setCompteClient(String compteClient) {
        this.compteClient = compteClient;
    }
    
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDecSante != null ? idDecSante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IccdMsDecMaritimeSante)) {
            return false;
        }
        IccdMsDecMaritimeSante other = (IccdMsDecMaritimeSante) object;
        if ((this.idDecSante == null && other.idDecSante != null) || (this.idDecSante != null && !this.idDecSante.equals(other.idDecSante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acl.iccd.core.entities.IccdMsDecMaritimeSante[ idDecSante=" + idDecSante + " ]";
    }
    
}
