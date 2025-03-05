package com.acl.formaliteagricultureapi.models;

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
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Entity
@Table(name = "DET_TRAITEMENT_PRODUIT", schema = "USERFMA")
@Getter
@Setter
@NoArgsConstructor
@Schema(implementation = DetTraitementProduit.class, description = "Entité liée à l'Exportation")
public class DetTraitementProduit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "S_DET_TRAITEMENT_PRODUIT")
    @SequenceGenerator(sequenceName = "S_DET_TRAITEMENT_PRODUIT", allocationSize = 1, name = "S_DET_TRAITEMENT_PRODUIT")
    @Column(name = "ID")
    private Long id;


    @Column(name = "TRAITEMENT")
    private String traitement;
    
    @Column(name = "NATURE_TRAITEMENT")
    private String natureTraitement;
    
    @Column(name = "CONTENEUR")
    private String conteneur;
    
    @Column(name = "CONDITIONNEMENT")
    private String conditionnement;
    
    @Column(name = "IDENTIFICATION")
    private String identification;
    
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
    private int duree;
    
    @Column(name = "TEMPERATURE")
    private String temperature;
    
    @Column(name = "TEMPERATURE_ENTREPOSAGE")
    private String temperatureEntreposage;
    
    @Column(name = "TEMPERATURE_TRANSPORT")
    private String temperatureTransport;
       	    
    
    @JoinColumn(name = "ID_PRODUIT", referencedColumnName = "ID")
    @ManyToOne
    private Produit produit;
    
    @JoinColumn(name = "ID_CERTIFICAT", referencedColumnName = "ID")
    @ManyToOne
    private Certificat certificat;

    @JoinColumn(name = "ID_PHYTOSANITAIRE", referencedColumnName = "ID")
    @ManyToOne
    private PhytoSanitaire phytoSanitaire;
        
    @JoinColumn(name = "ID_SOCIETE", referencedColumnName = "ID")
    @ManyToOne
    private Societe societeTraitement;
    
    public DetTraitementProduit(String traitement, String natureTraitement,
			Date dateTraitement, Date heureDebTrait, @Size(max = 254) String substanceActive,
			String concentration, int duree, String temperature, String temperatureEntreposage,
			String temperatureTransport, Produit produit, Societe societeTraitement,
			PhytoSanitaire phytoSanitaire) {
		super();
		this.traitement = traitement;
		this.natureTraitement = natureTraitement;
		this.dateTraitement = dateTraitement;
		this.heureDebTrait = heureDebTrait;
		this.substanceActive = substanceActive;
		this.concentration = concentration;
		this.duree = duree;
		this.temperature = temperature;
		this.temperatureEntreposage = temperatureEntreposage;
		this.temperatureTransport = temperatureTransport;
		this.produit = produit;
		this.societeTraitement = societeTraitement;
		this.phytoSanitaire = phytoSanitaire;
	}
    
    
    public DetTraitementProduit(String traitement, String natureTraitement,
			String conteneur, String conditionnement, String identification, String temperatureEntreposage,
			String temperatureTransport, Produit produit, Societe societeTraitement, Certificat certificat) {
		super();
		this.traitement = traitement;
		this.natureTraitement = natureTraitement;
		this.conteneur = conteneur;
		this.conditionnement = conditionnement;
		this.identification = identification;
		this.temperatureEntreposage = temperatureEntreposage;
		this.temperatureTransport = temperatureTransport;
		this.produit = produit;
		this.societeTraitement = societeTraitement;
		this.certificat = certificat;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetTraitementProduit that = (DetTraitementProduit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
