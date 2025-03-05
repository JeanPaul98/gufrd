package shared;

import com.acl.iccd.core.entities.IccdFormaliteMinisterielle;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author kol on 5/5/21
 */
@Entity
@Table(name = "ICCD_PERMIS", schema = "SIPEDBA")
@SequenceGenerator(name = "S_ICCD_PERMIS", sequenceName = "S_ICCD_PERMIS", allocationSize = 1)
@XmlRootElement
public class IccdPermis implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ICCD_PERMIS")
    @Column(name = "ID_PERMIS")
    private Long idPermis;

    @Size(max = 254)
    @Column(name = "DIRECTION_PREFECTORAL")
    private String directionPrefectorl;
    @Size(max = 254)
    @Column(name = "NUM_DRFDDPN")
    private String numDrfddpn;
    @Size(max = 254)
    @Column(name = "NUM_PERMIS")
    private String numPermis;
    @Size(max = 254)
    @Column(name = "NUM_AUTORISATION")
    private String numAutorisation;
    @Column(name = "QUANTITE")
    private double quantite;
    @Size(max = 254)
    @Column(name = "NUM_SERIE")
    private String numSerie;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_PROCES_VERBAL")
    private Date dateProcesVerbal;
    @Size(max = 254)
    @Column(name = "NUM_PROCES_VERBAL")
    private String numProcesVerval;
    @Size(max = 254)
    @Column(name = "BENEFICIAIRE")
    private String beneficiaire;
    @Size(max = 254)
    @Column(name = "RESIDENCE")
    private String residence;
    @Size(max = 254)
    @Column(name = "PROFESSION")
    private String profession;
    @Size(max = 254)
    @Column(name = "VERSEMENT")
    private String versement;
    @Size(max = 254)
    @Column(name = "NUM_QUITANCE")
    private String numQuitance;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_DELIVRANCE")
    private Date dateDelivrance;
    @Size(max = 254)
    @Column(name = "LIEU_DELIVRANCE")
    private String lieuDelivrance;
    @Size(max = 254)
    @Column(name = "DELIVREUR")
    private String delivreur;
    @Size(max = 254)
    @Column(name = "PROVENANCE_PRODUIT")
    private String provenanceProduit;
    @Size(max = 254)
    @Column(name = "PAYS_PROVENANCE")
    private String paysProvenance;
    @Size(max = 254)
    @Column(name = "ITINERAIRE")
    private String itineraire;
    @Size(max = 254)
    @Column(name = "TITULAIRE")
    private String titulaire;
    @Size(max = 254)
    @Column(name = "NUM_CARTE_PROFESSION")
    private String numCarteProfession;
    @Size(max = 254)
    @Column(name = "QUITANCE_DEDOUANEMENT")
    private String quitanceDedouanement;
    @Size(max = 254)
    @Column(name = "PERMIS_COUPE")
    private String permisCoupe;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_SIGNATURE")
    private Date dateSignature;
    @Size(max = 254)
    @Column(name = "DELAI_TRANSPORT")
    private String delaiTransport;
    @Size(max = 254)
    @Column(name = "DESTINATION_PRODUIT")
    private String destinationProduit;
    @Size(max = 254)
    @Column(name = "DIRECTEUR_PREFECTORAL")
    private String directeurPrefectoral;

    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "ID_FORMALITE")
    @ManyToOne
    private IccdFormaliteMinisterielle idFm;

    public IccdPermis() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IccdPermis that = (IccdPermis) o;
        return Objects.equals(idPermis, that.idPermis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPermis);
    }

    public Long getIdPermis() {
        return idPermis;
    }

    public void setIdPermis(Long idPermis) {
        this.idPermis = idPermis;
    }

    public String getDirectionPrefectorl() {
        return directionPrefectorl;
    }

    public void setDirectionPrefectorl(String directionPrefectorl) {
        this.directionPrefectorl = directionPrefectorl;
    }

    public String getNumDrfddpn() {
        return numDrfddpn;
    }

    public void setNumDrfddpn(String numDrfddpn) {
        this.numDrfddpn = numDrfddpn;
    }

    public String getNumPermis() {
        return numPermis;
    }

    public void setNumPermis(String numPermis) {
        this.numPermis = numPermis;
    }

    public String getNumAutorisation() {
        return numAutorisation;
    }

    public void setNumAutorisation(String numAutorisation) {
        this.numAutorisation = numAutorisation;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public Date getDateProcesVerbal() {
        return dateProcesVerbal;
    }

    public void setDateProcesVerbal(Date dateProcesVerbal) {
        this.dateProcesVerbal = dateProcesVerbal;
    }

    public String getNumProcesVerval() {
        return numProcesVerval;
    }

    public void setNumProcesVerval(String numProcesVerval) {
        this.numProcesVerval = numProcesVerval;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getVersement() {
        return versement;
    }

    public void setVersement(String versement) {
        this.versement = versement;
    }

    public String getNumQuitance() {
        return numQuitance;
    }

    public void setNumQuitance(String numQuitance) {
        this.numQuitance = numQuitance;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    public String getDelivreur() {
        return delivreur;
    }

    public void setDelivreur(String delivreur) {
        this.delivreur = delivreur;
    }

    public String getProvenanceProduit() {
        return provenanceProduit;
    }

    public void setProvenanceProduit(String provenanceProduit) {
        this.provenanceProduit = provenanceProduit;
    }

    public String getPaysProvenance() {
        return paysProvenance;
    }

    public void setPaysProvenance(String paysProvenance) {
        this.paysProvenance = paysProvenance;
    }

    public String getItineraire() {
        return itineraire;
    }

    public void setItineraire(String itineraire) {
        this.itineraire = itineraire;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public String getNumCarteProfession() {
        return numCarteProfession;
    }

    public void setNumCarteProfession(String numCarteProfession) {
        this.numCarteProfession = numCarteProfession;
    }

    public String getQuitanceDedouanement() {
        return quitanceDedouanement;
    }

    public void setQuitanceDedouanement(String quitanceDedouanement) {
        this.quitanceDedouanement = quitanceDedouanement;
    }

    public String getPermisCoupe() {
        return permisCoupe;
    }

    public void setPermisCoupe(String permisCoupe) {
        this.permisCoupe = permisCoupe;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public String getDelaiTransport() {
        return delaiTransport;
    }

    public void setDelaiTransport(String delaiTransport) {
        this.delaiTransport = delaiTransport;
    }

    public String getDestinationProduit() {
        return destinationProduit;
    }

    public void setDestinationProduit(String destinationProduit) {
        this.destinationProduit = destinationProduit;
    }

    public String getDirecteurPrefectoral() {
        return directeurPrefectoral;
    }

    public void setDirecteurPrefectoral(String directeurPrefectoral) {
        this.directeurPrefectoral = directeurPrefectoral;
    }

    public IccdFormaliteMinisterielle getIdFm() {
        return idFm;
    }

    public void setIdFm(IccdFormaliteMinisterielle idFm) {
        this.idFm = idFm;
    }

    @Override
    public String toString() {
        return "IccdPermis{" +
                "idPermis=" + idPermis +
                '}';
    }
}
