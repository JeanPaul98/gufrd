package com.acl.formaliteagricultureapi.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 22/08/2024
 * @project formalite-agriculture-api
 */

@Getter
@Setter
@Schema(description = "DetPhytosanitaireProduitDto")
@ToString
public class DetPhytosanitaireProduitDto {

    @Schema(description = "JPA Produit: quantite du produit",
            name="quantite",
            required=false)
	private int quantite;

    @Schema(description = "JPA Produit:  produit concerné ",
            name="produit",
            required=false)
    private ProduitDto produit;

    @Schema(description = "JPA Produit: fournisseur du produit",
            name="fournisseur",
            required=false)
    private String fournisseur;

    @Schema(description = "JPA Produit: origine du produit",
            name="origine",
            required=false)
    private String origine;

    @Schema(description = "JPA Produit: référence du conteneur",
            name="conteneur",
            required=false)
    private String conteneur;

    @Schema(description = "JPA Produit: description supplémentaire de l'envoi",
            name="descriptionEnvoi",
            required=false)
    private String descriptionEnvoi;

    @Schema(description = "JPA Produit: nombre total de colis",
            name="nombreColis",
            required=false)
    private int nombreColis;


    public DetPhytosanitaireProduitDto(ProduitDto produitDto, int quantite,
                                       String conteneur, String fournisseur,
                                       String descriptionEnvoi, String paysEtLieuOrigin,
                                       int nombreColis) {
        this.produit = produitDto;
        this.quantite = quantite;
        this.conteneur = conteneur;
        this.fournisseur = fournisseur;
        this.descriptionEnvoi = descriptionEnvoi;
        this.nombreColis = nombreColis;
        this.origine = paysEtLieuOrigin;
    }
}
