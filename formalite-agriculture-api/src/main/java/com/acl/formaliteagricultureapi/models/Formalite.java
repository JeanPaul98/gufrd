/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acl.formaliteagricultureapi.models;



import com.acl.formaliteagricultureapi.models.enumeration.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Olivier
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FORMALITE", schema = "USERFMA")
@Schema(implementation = Formalite.class)
public class Formalite implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_FORMALITE")
    @SequenceGenerator(sequenceName = "S_FORMALITE", allocationSize = 1, name = "S_FORMALITE")
    @Column(name = "ID")
    private Long id;

    @Size(max = 254)
    @Column(name = "NUM_GENERE")
    private String numGenere;

    @Size(max = 254)
    @Column(name = "TYPE_DMD")
    private String typeDmd;

    @Size(max = 254)
    @Column(name = "DECISION")
    private String decision;

    @Column(name = "OBSERVATIONS")
    private String observations;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Column(name = "MONTANT_REDEVANCE")

    private double montantRedevance;

    @Column(name = "MOTIF_ANNULATION")
    private String motifAnnulation;

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    private Chaine chaine;

    @Column(columnDefinition = "varchar(50)", name = "STATUT_PAIEMENT")
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;

    @Column(columnDefinition = "varchar(50)", name = "STATUT_DEMANDE")
    @Enumerated(EnumType.STRING)
    private StatutDemande statutDemande;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_REGIME")
    private TypeRegime typeRegime;

    @Column(name = "CODE_CLIENT")
    private String compteClient;

    @Column(name = "DATE_DMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDmd;

    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Column(name = "DATE_ANNULATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnnulation;

    @Column(name = "DATE_REJET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Column(name = "DATE_ACCEPTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;

    @Column(name = "DATE_SOUMISE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumise;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "nom_navire")
    private String nomNavire;

    @Column(name = "imo_navire")
    private String imo;

    @Column(name = "num_atp_navire")
    private String atp;

    @Column(name = "affreteur")
    private String affreteur;

    @Column(name = "NUMERO_DOSSIER")
    private String numeroDossier;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_AT")
    private Date updateAt;

    @Column(name = "code_port")
    private String codePort;

    @Column(name = "nom_importateur")
    private String nomImportateur;

    @Column(name = "NUMERO_AGREMENT_TRANSIT")
    private String numeroAgrementTransit;


    @Column(name = "compte_agent_accepter")
    private String compteAgentAccepter;

    @Column(name = "compte_agent_rejet")
    private String compteAgentRejet;

    @Column(name = "compte_agent_traiter")
    private String compteAgentTraiter;

    @Column(name = "NIF")
    private String nif;

    @ManyToOne
    @JoinColumn(name = "ID_STRUCTURE", referencedColumnName = "id")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "ID_AUTORISATION_AGREMENT", referencedColumnName = "ID")
    private AutorisationAgrement autorisationAgrement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_autorisation", referencedColumnName = "id")
    private Autorisation autorisation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_inspection", referencedColumnName = "id")
    private PhytoSanitaire phytoSanitaire;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_certificat", referencedColumnName = "id")
    private Certificat certificat;

    @ManyToOne()
    @JoinColumn(name = "id_societe", referencedColumnName = "id")
    private Societe societe;



    public Formalite(String atp,String nomNavire,String imo,String affreteur,
                     String compteClient, String nomImportateur){
        this.atp=atp;
        this.nomNavire=nomNavire;
        this.imo=imo;
        this.affreteur=affreteur;
        this.compteClient=compteClient;
        this.nomImportateur=nomImportateur;
    }

    public Formalite(String atp, String nomNavire, String immo, String affreteur) {
        this.atp=atp;
        this.nomNavire=nomNavire;
        this.imo=immo;
        this.affreteur=affreteur;

    }

    public Formalite(String compteClient, String nomImportateur) {
        this.compteClient = compteClient;
        this.nomImportateur = nomImportateur;
    }

    public Formalite(String atp, String nomNavire, String imo, String affreteur,
                     String compteClient) {

        this.atp=atp;
        this.nomNavire=nomNavire;
        this.imo=imo;
        this.affreteur=affreteur;
        this.compteClient=compteClient;
    }

    public Formalite(String compteClient, Autorisation autorisation,
                     AutorisationAgrement autorisationAgrement) {
        this.compteClient = compteClient;
        this.autorisation = autorisation;
        this.autorisationAgrement = autorisationAgrement;
    }

    public Formalite(String compteClient, Autorisation autorisation, AutorisationAgrement
            autorisationAgrement, Etat etat, StatutPaiement statutPaiement, Chaine chaine) {
        this.compteClient = compteClient;
        this.autorisation = autorisation;
        this.autorisationAgrement = autorisationAgrement;
        this.etat = etat;
        this.statutPaiement = statutPaiement;
        this.chaine = chaine;
    }

    public Formalite(String compteClient, Autorisation autorisation,
                     Etat etat, StatutPaiement statutPaiement, Chaine chaine,
                     String numeroAgrement) {
        this.compteClient = compteClient;
        this.autorisation = autorisation;
        this.etat = etat;
        this.statutPaiement = statutPaiement;
        this.chaine = chaine;
        this.numeroAgrementTransit = numeroAgrement;
    }

    public Formalite(String compteClient, AutorisationAgrement autorisationAgrement, Etat etat, StatutPaiement statutPaiement, Chaine chaine) {
        this.compteClient = compteClient;
        this.etat = etat;
        this.statutPaiement = statutPaiement;
        this.chaine = chaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formalite formalite = (Formalite) o;
        return Objects.equals(id, formalite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
