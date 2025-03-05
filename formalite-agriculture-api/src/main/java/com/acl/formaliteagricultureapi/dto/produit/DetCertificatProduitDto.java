package com.acl.formaliteagricultureapi.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author kol on 27/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DetCertificatProduitDto {

    @Schema(description = "JPA Produit: code produit",
            name="quantite",
            required=false)
    private int quantite;

    @Schema(description = "JPA Produit: code produit",
            name="produit",
            required=false)
    private ProduitDto produit;

    @Schema(description = "JPA Produit: code produit",
            name="fournisseur",
            required=false)
    private String fournisseur;

    @Schema(description = "JPA Produit: code produit",
            name="origine",
            required=false)
    private String origine;

    @Schema(description = "JPA Produit: code produit",
            name="conteneur",
            required=false)
    private String conteneur;

    @Schema(description = "JPA Produit: sexe de l'animal",
            name="sexe",
            required=false)
    private String sexe;

    @Schema(description = "JPA Produit: code produit",
            name="nombreColis",
            required=false)
    private int nombreColis;

    @Schema(description = "JPA Produit: code produit",
            name="nombre",
            required=false)
    private int nombre;


    @Schema(description = "JPA Produit: poids",
            name="poidsTotal",
            required=false)
    private double poidsTotal;

    @Schema(description = "JPA Produit: Conditionnement",
            name="conditionnement",
            required=false)
    private String conditionnement;

    @Schema(description = "JPA Produit: race",
            name="race",
            required=false)
    private String race;

    @Schema(description = "JPA Produit: race",
            name="espece",
            required=false)
    private String espece;

    @Schema(description = "JPA Produit: nature du Produit",
            name="natureProduit",
            required=false)
    private String natureProduit;

    public DetCertificatProduitDto(ProduitDto produitDto,
                                   int quantite,
                                   int nombre,
                                   String natureProduit,
                                   int nombreColis,
                                   String conditionnement,
                                   String paysOrigineProvenance,
                                   String race,
                                   String espece,
                                   String fournisseur,
                                   String sexe,
                                   String conteneur,
                                   double poidsTotal) {

        this.produit = produitDto;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.conteneur = conteneur;
        this.sexe = sexe;
        this.conditionnement = conditionnement;
        this.race = race;
        this.espece = espece;
        this.nombre = nombre;
        this.natureProduit = natureProduit;
        this.nombreColis = nombreColis;
        this.origine = paysOrigineProvenance;
        this.poidsTotal = poidsTotal;
    }
}
