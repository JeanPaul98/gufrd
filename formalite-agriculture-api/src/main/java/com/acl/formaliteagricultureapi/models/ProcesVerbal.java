package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Zansouyé
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PROCES_VERBAL", schema = "USERFMA")
@Schema(implementation = ProcesVerbal.class)
public class ProcesVerbal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_PROCES_VERBAL")
    @SequenceGenerator(sequenceName = "S_PROCES_VERBAL", allocationSize = 1, name = "S_PROCES_VERBAL")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "DATE_PV")
    @Temporal(TemporalType.DATE)
    private Date datePv;

    @Column(name = "NOM_AGENT")
    private String nomAgent;

    @Column(name = "PRENOM_AGENT")
    private String prenomAgent;

    @Column(name = "POINT_ENTREE")
    private String pointEntree;

    @Column(name = "DATE_INSPECTION")
    @Temporal(TemporalType.DATE)
    private Date dateInspection;

    @Column(name = "LIEU_INSPECTION")

    private String lieuInspection;


    @Column(name = "NUMERO_FACTURE")
    private String numeroFacture;


    @Column(name = "RESULTAT_INSPECTION")
    private String resultatInspection;


    @Column(name = "NUM_CERTIFICAT_PHYTO")
    private String numCertifPhyto;

    @Column(name = "HEURE_DEBUT_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebutInspection;

    @Column(name = "HEURE_FIN_INSPECTION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureFinInspection;


    @Column(name = "NOM_PRENOMS_EXPEDITEUR")
    private String nomPrenomsExpediteur;

    @Column(name = "RAISON_SOCIALE_EXP")
    private String raisonSocialeExp;

    @Column(name = "ADRESSE_EXPEDITEUR")
    private String adresseExpediteur;

    @Column(name = "NOM_PRENOMS_DESTINATAIRE")
    private String nomPrenomsDestinataire;

    @Column(name = "RAISON_SOCIALE_DESTINATAIRE")
    private String raisonsocialeDestinataire;

    @Column(name = "ADRESSE_DESTINATAIRE")
    private String adresseDestinataire;

    @Column(name = "NUM_ENREG_MOYTRANSP")
    private String numEnregMoytransp;

    /*
    @Column(name = "DATE_ARRIVEE_MOYTRANSP")
    @Temporal(TemporalType.DATE)
    private Date dateArriveeMoytransp;*/

    @Column(name = "QTE_POIDS_NET")
    private double qtePoidsNet;

    @Column(name = "QTE_POIDS_BRUT")
    private double qtePoidsBrut;


    @Column(name = "TRAIT_SUBSACTIVE")
    private String traitSubsactive;


    @Column(name = "CHEF_POSTE")
    private String chefPoste;


    @Column(name = "VOL")
    private String vol;


    @Column(name = "TRAIT_CONCENTRATION")
    private String traitConcentration;

    @Column(name = "TRAIT_PDT_TEMPERATURE")
    private Double traitPdtTemperature;

    @Column(name = "TRAIT_PRDT_DUREE")
    private int traitPrdtDuree;


    @Column(name = "NOM_NAVIRE")
    private String nomNavire;


    @Column(name = "AFFRETEUR")
    private String affreteur;


    @Column(name = "PROVENANCE")
    private String provenance;

    @Column(name = "DATE_DEPART_PREVUE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDepartPrevue;

    @Column(name = "PARTIE_NAVIRE_VIS")
    private String partieNavireVisitee;


    @Column(name = "EMPLACEMENT")
    private String emplacement;


    @Column(name = "MESURE")
    private String mesure;


    @Column(name = "NUM_CAMION")
    private String numCamion;


    @Column(name = "REMARQUES_PARTI")
    private String remarque;

    @Column(name = "DATE_DEL_CERTPHYTO")
    @Temporal(TemporalType.DATE)
    private Date dateDelCertphyto;


    @Column(name = "NOM_COMMANDANT")
    private String nomCommandant;


    @Column(name = "OFFICIER_NAVIRE_PRESENT")
    private String officierNavirePresent;


    @Column(name = "VIA")
    private String via;

    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Column(name = "DATE_ARRIVEE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrivee;

    @Column(name = "DATE_APPLICATION")
    @Temporal(TemporalType.DATE)
    private Date dateApplication;

    @Column(name = "CONFORMITE_DU_CERT")
    private BigInteger conformiteDuCert;


    @Column(name = "NUM_PERMIS_IMPORT")
    private String numPermisImport;

    @Column(name = "DATE_DEL_PERMIS_IMP")
    @Temporal(TemporalType.DATE)
    private Date dateDelPermisImp;

    @Column(name = "CONFORMITE_PERMIS_IMP")
    private short conformitePermisImp;


    @Column(name = "AUTRES_CERTIF")
    private String autresCertif;


    @Column(name = "NUM_PERMIS_EXP")
    private String numPermisExp;

    @Column(name = "DATE_DEL_PERMIS_EXPORT")
    @Temporal(TemporalType.DATE)
    private Date dateDelPermisExport;

    @Column(name = "CONFORMITE_PERMIS_EXPORT")
    private short conformitePermisExport;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "CONTROLE_TECH")
    private String controleTech;

    @Column(name = "AGENT_PV")
    private String agentPV;

    /*
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

     @Column(name = "PRODUITS_PHARMACEUTIQUES")
    private String produitsPharmaceutiques;


    @Column(name = "NOM_COMMERCIAL")
    private String nomCommercial;


    @Column(name = "LIEU_POSTE")
    private String lieuPoste;


    @Column(name = "CONTENEUR")
    private String conteneur;

    @Column(name = "COMPTE_CLIENT_PV")
    private String compteClientPV;


     */


    @Column(name = "DECLARATION_SUPLEMENTAIRE")
    private String declarationSuplementaire;

    @JoinColumn(name = "ID_TYPE_PV", referencedColumnName = "id")
    @ManyToOne
    private TypePv typePv;


    @JoinColumn(name = "ID_FORMALITE", referencedColumnName = "id")
    @ManyToOne
    private Formalite formalite;


    public ProcesVerbal(String via, Date dateInspection, Date dateDepartPrevue,
                        String partieNavireVisitee, String commandant,
                        String officierNavire, String lieuInspection,
                        Date datePv, String remarquesParti, String agentPV) {

        this.via =via;
        this.dateInspection = dateInspection;
        this.dateDepartPrevue = dateDepartPrevue;
        this.partieNavireVisitee = partieNavireVisitee;
        this.nomCommandant = commandant;
        this.officierNavirePresent = officierNavire;
        this.lieuInspection = lieuInspection;
        this.datePv = datePv;
        this.remarque=remarquesParti;
        this.agentPV=agentPV;
    }

    public ProcesVerbal(String via, Date dateInspection, Date dateDepartPrevue,
                        String partieNavireVisitee, String commandant, String officierNavire,
                        String lieuInspection, Date datePv, String remarque, String agentPV,
                        String expediteur, String destinataire, String resultatInspection) {
        this.via =via;
        this.dateInspection = dateInspection;
        this.dateDepartPrevue = dateDepartPrevue;
        this.partieNavireVisitee = partieNavireVisitee;
        this.nomCommandant = commandant;
        this.officierNavirePresent = officierNavire;
        this.lieuInspection = lieuInspection;
        this.datePv = datePv;
        this.remarque=remarque;
        this.agentPV=agentPV;
        this.nomPrenomsExpediteur = expediteur;
        this.nomPrenomsDestinataire = destinataire;
        this.resultatInspection=resultatInspection;
    }

    public ProcesVerbal(String pointEntree, String lieuInspection, Date dateInspection, Date dateApplication,
                        String expediteur, String destinataire, String numeroEnregistrement, String controleTechnique,
                        String resultatInspection, Date datePv, String remarque, String agentPV) {
        this.pointEntree = pointEntree;
        this.lieuInspection = lieuInspection;
        this.dateInspection = dateInspection;
        this.dateApplication = dateApplication;
        this.nomPrenomsExpediteur = expediteur;
        this.nomPrenomsDestinataire = destinataire;
        this.numEnregMoytransp = numeroEnregistrement;
        this.controleTech = controleTechnique;
        this.resultatInspection=resultatInspection;
        this.datePv = datePv;
        this.remarque=remarque;
        this.agentPV=agentPV;
    }
}
