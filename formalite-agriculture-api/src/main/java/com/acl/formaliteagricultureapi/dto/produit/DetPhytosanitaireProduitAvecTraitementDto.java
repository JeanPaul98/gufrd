package com.acl.formaliteagricultureapi.dto.produit;

import java.util.Date;

import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DetPhytosanitaireProduitAvecTraitementDto")
public class DetPhytosanitaireProduitAvecTraitementDto {

	@Schema(description = "JPA Produit: code produit",
			name="produit",
			required=false)
    private ProduitDto produit;

	@Schema(description = "JPA Produit: code produit",
			name="quantite",
			required=false)
	private int quantite;

	@Schema(description = "JPA Produit: code produit",
			name="nombreColis",
			required=false)
	private int nombreColis;

	@Schema(description = "JPA Produit: code produit",
			name="paysEtLieuOrigin",
			required=false)
	private String paysEtLieuOrigin;

	@Schema(description = "JPA Produit: code produit",
			name="moyenTransport",
			required=false)
	private String moyenTransport;

	@Schema(description = "JPA Produit: code produit",
			name="pointEntree",
			required=false)
	private String pointEntree;

	@Schema(description = "JPA Produit: code produit",
			name="dateHeureEmbarquement",
			required=false)
	private Date dateHeureEmbarquement;

	@Schema(description = "JPA Produit: code produit",
			name="traitementProduit",
			required=false)
	private String traitementProduit;

	@Schema(description = "JPA Produit: code produit",
			name="renseignCompl",
			required=false)
	private String renseignCompl;

	@Schema(description = "JPA Produit: code produit",
			name="societeTraitement",
			required=false)
	private SocieteDto societeTraitement;

	@Schema(description = "JPA Produit: code produit",
			name="dateTraitement",
			required=false)
	private Date dateTraitement;

	@Schema(description = "JPA Produit: code produit",
			name="heureDebTrait",
			required=false)
	private Date heureDebTrait;

	@Schema(description = "JPA Produit: code produit",
			name="substanceActive",
			required=false)
	private String substanceActive;

	@Schema(description = "JPA Produit: code produit",
			name="concentration",
			required=false)
	private String concentration;

	@Schema(description = "JPA Produit: code produit",
			name="duree",
			required=false)
	private float duree;

	@Schema(description = "JPA Produit: code produit",
			name="temperature",
			required=false)
	private String temperature;
	
	
	
}
