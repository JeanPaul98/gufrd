package com.acl.formaliteagricultureapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author A. JULES
 */
@Entity
@Table(name = "DET_CERTIFICAT_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetCertificatProduit.class)
public class DetCertificatProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_CERTIFICAT_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_CERTIFICAT_PRODUIT", allocationSize = 1, name = "S_DET_CERTIFICAT_PRODUIT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM_SCIENTIFIQUE")
    private String nomScientifique;
    
    @Column(name = "POIDS_TOTAL")
    private double poidsTotal;

    @Column(name = "QUANTITE")
    private int quantite;

    @Column(name = "NOMBRE")
    private int nombre;


    @Column(name = "NOMBRE_COLIS")
    private int nombreColis;

    @Column(name = "RACE")
    private String race;

    @Column(name = "ESPECE")
    private String espece;
    
    @Column(name = "CONDITIONNEMENT")
    private String conditionnement;
    
    @Column(name = "CONTENEUR")
    private String conteneur;
    
    @Column(name = "IDENTIFICATION")
    private String identification;
    
    @Column(name = "LOT_IDENTIFIE")
    private String lotIdentifie;
    
    @Column(name = "NATURE_EMBALLAGE")
    private String natureEmballage;
    
    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;
    
    @Column(name = "PAYS_ORIGINE_PROVENANCE")
    private String paysOrigineProvenance;
    
    @Column(name = "PAYS_DESTINATION")
    private String paysDestination;
    
    @Column(name = "LIEU_CHARGEMENT")
    private String lieuChargement;
    
    @Column(name = "NATURE_PRODUIT")
    private String natureProduit;
    
    @Column(name = "SEXE")
    private String sexe;
    
    @Column(name = "POINT_ENTREE")
    private String pointEntree;

    @Column(name = "FOURNISSEUR")
    private String fournisseur;

    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "ID_CERTIFICAT", referencedColumnName = "ID")
    @ManyToOne
    private Certificat certificat;
    
    @JoinColumn(name = "ID_CERTIFICAT_SAISIE", referencedColumnName = "ID")
    @ManyToOne
    private CertificatSaisie certificatSaisie;



    public DetCertificatProduit(Produit produit, Certificat certificat, int quantite,
                                double poidsTotal, String conditionnement, String race,
                                String natureProduit, String espece, int nombre,
                                String fournisseur, String conteneur, String origine, String sexe,
                                int nombreColis) {
        this.produit = produit;
        this.certificat = certificat;
        this.quantite = quantite;
        this.poidsTotal = poidsTotal;
        this.conditionnement = conditionnement;
        this.race = race;
        this.natureProduit = natureProduit;
        this.espece = espece;
        this.nombre = nombre;
        this.fournisseur = fournisseur;
        this.conteneur = conteneur;
        this.paysOrigineProvenance = origine;
        this.sexe = sexe;
        this.nombreColis = nombreColis;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetCertificatProduit that = (DetCertificatProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
