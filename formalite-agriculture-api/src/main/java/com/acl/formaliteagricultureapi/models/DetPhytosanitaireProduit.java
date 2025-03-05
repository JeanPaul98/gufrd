package com.acl.formaliteagricultureapi.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DET_PHYTOSANITAIRE_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetPhytosanitaireProduit.class)
public class DetPhytosanitaireProduit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_PHYTOSANITAIRE_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_PHYTOSANITAIRE_PRODUIT", allocationSize = 1, name = "S_DET_PHYTOSANITAIRE_PRODUIT")
    @Column(name = "ID")
    private Long id;
    
      
    @Column(name = "PAYS_ET_LIEU_ORIGINE")
    private String paysEtLieuOrigin;

    @Column(name = "QUANTITE")
    private int quantite;
    
   
    @Column(name = "FOURNISSEUR")
    private String fournisseur;
    
    @Column(name = "CONTENEUR")
    private String conteneur;

    @Column(name = "DESCRIPTION_ENVOI")
    private String descriptionEnvoi;
    
    @Column(name = "NOMBRE_COLIS")
    private int nombreColis;
    
    @Size(max = 254)
    @Column(name = "MOYEN_TRANSPORT")
    private String moyenTransport;
    
    @Size(max = 254)
    @Column(name = "POINT_ENTREE")
    private String pointEntree;
    
    @Column(name = "DATE_HEURE_EMBARQUEMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureEmbarquement;
    
    @Size(max = 3)
    @Column(name = "TRAITEMENT_PRODUIT")
    private String traitementProduit;
    
    @Column(name = "DATE_TRAITEMENT")
    @Temporal(TemporalType.DATE)
    private Date dateTraitement;
    
    @Column(name = "HEURE_DEB_TRAIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebTrait;
    
    @Size(max = 254)
    @Column(name = "SUBSTANCE_ACTIVE")
    private String substanceActive;
    
    @Size(max = 254)
    @Column(name = "CONCENTRATION")
    private String concentration;
    
    @Column(name = "DUREE")
    private float duree;
    
    @Column(name = "TEMPERATURE")
    private String temperature;
    
    @Size(max = 254)
    @Column(name = "RENSEIGN_COMPL")
    private String renseignCompl;
    
    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID")
    @ManyToOne
    private Produit produit;

    @JoinColumn(name = "ID_PHYTOSANITAIRE", referencedColumnName = "ID")
    @ManyToOne
    private PhytoSanitaire phytoSanitaire;
        
    @JoinColumn(name = "ID_SOCIETE", referencedColumnName = "ID")
    @ManyToOne
    private Societe societeTraitement;
    
    
	public DetPhytosanitaireProduit(Produit produit, int quantite, String fournisseur, String paysEtLieuOrigin,
			PhytoSanitaire phytoSanitaire) {		
		this.produit = produit;
		this.quantite = quantite;
		this.fournisseur = fournisseur;
		this.paysEtLieuOrigin = paysEtLieuOrigin;
		this.phytoSanitaire = phytoSanitaire;
	}
    
	//Inspection phytosanitaire pour l’obtention de certificat phytosanitaire sans traitement
	public DetPhytosanitaireProduit(Produit produit, int quantite, int nombreColis, 
			String paysEtLieuOrigin,
			String moyenTransport, String pointEntree,Date dateHeureEmbarquement,
			String traitementProduit,
			String renseignCompl,
			PhytoSanitaire phytoSanitaire) {		
		this.produit = produit;
		this.quantite = quantite;
		this.nombreColis = nombreColis;
		this.paysEtLieuOrigin = paysEtLieuOrigin;
		this.moyenTransport = moyenTransport;
		this.pointEntree    = pointEntree;
		this.dateHeureEmbarquement = dateHeureEmbarquement;
		this.traitementProduit = traitementProduit;
		this.renseignCompl = renseignCompl;
		this.phytoSanitaire = phytoSanitaire;
	}
	
	//Inspection Phytosanitaire pour l’obtention de certificat phytosanitaire avec traitement
		public DetPhytosanitaireProduit(Produit produit, int quantite, int nombreColis, 
				String paysEtLieuOrigin,
				String moyenTransport, String pointEntree,Date dateHeureEmbarquement,
				String traitementProduit,
				Societe societeTraitement,
				Date dateTraitement,
				Date heureDebTrait,
				String substanceActive,
				String concentration,
				float duree,
				String temperature,
				String renseignCompl,
				PhytoSanitaire phytoSanitaire) {		
			this.produit = produit;
			this.quantite = quantite;
			this.nombreColis = nombreColis;
			this.paysEtLieuOrigin = paysEtLieuOrigin;
			this.moyenTransport = moyenTransport;
			this.pointEntree    = pointEntree;
			this.dateHeureEmbarquement = dateHeureEmbarquement;
			this.traitementProduit = traitementProduit;
			this.societeTraitement = societeTraitement;
			this.substanceActive = substanceActive;
			this.concentration  = concentration ;
			this.dateTraitement =  dateTraitement;
			this.heureDebTrait  =  heureDebTrait;
			this.duree  = duree;
			this.temperature =  temperature;
			this.renseignCompl = renseignCompl;
			this.phytoSanitaire = phytoSanitaire;
		}

    public DetPhytosanitaireProduit(Produit produit, PhytoSanitaire phytoSanitaire,
                                    int quantite, String descriptionEnvoi, String origine,
                                    String conteneur, String fournisseur,
                                    int nombreColis) {
        this.produit = produit;
        this.phytoSanitaire = phytoSanitaire;
        this.quantite = quantite;
        this.descriptionEnvoi = descriptionEnvoi;
        this.fournisseur = fournisseur;
        this.paysEtLieuOrigin = origine;
        this.conteneur = conteneur;
        this.nombreColis = nombreColis;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetPhytosanitaireProduit that = (DetPhytosanitaireProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

	

    

}
