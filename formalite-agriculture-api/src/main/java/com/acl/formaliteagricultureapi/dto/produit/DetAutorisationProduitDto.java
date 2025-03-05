package com.acl.formaliteagricultureapi.dto.produit;

import com.acl.formaliteagricultureapi.models.Produit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "DetAutorisationProduitDto")
public class DetAutorisationProduitDto {

    @Schema(description = "JPA Produit: code produit",
            name="quantite",
            required=false)
    private int quantite;

    @Schema(description = "JPA Produit: code produit",
            name="unite",
            required=false)
    private String unite;

    @Schema(description = "JPA Produit: code produit",
            name="produit",
            required=false)
    private ProduitDto produit;

    @Schema(description = "JPA Produit: code produit",
            name="nombreCarton",
            required=false)
    private int nombreCarton;

    @Schema(description = "JPA Produit: code produit",
            name="origine",
            required=false)
    private String origine;


    @Schema(description = "JPA Produit : Provenance du produit",
            name="provenance",
            required=false)
    private String provenance;

    @Schema(description = "JPA Produit : conteneur",
            name="conteneur")
    private String conteneur;

    @Schema(description = "JPA Produit : Poids net",
            name="poidNets",
            required=false)
    private double poidNets;



    public DetAutorisationProduitDto(ProduitDto produitDto, int quantite, String provenance,
                                     int nombreCarton,
                                     String unite, String origine, double poidNet) {
        this.produit = produitDto;
        this.quantite = quantite;
        this.provenance = provenance;
        this.nombreCarton = nombreCarton;
        this.unite = unite;
        this.origine = origine;
        this.poidNets = poidNet;
    }
}
