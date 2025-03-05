package com.acl.formaliteagricultureapi.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Zansouyé on 11/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description = "ReportDetAutorisationProduitDto")
public class ReportDetAutorisationProduitDto {
    @Schema(description = "JPA Produit: code produit",
            name="quantite",
            required=false)
    private int quantite;

    @Schema(description = "JPA Produit: code produit",
            name="unite",
            required=false)
    private String unite;

    @Schema(description = "JPA Produit: Libelle produit",
            name="libelleProduit",
            required=false)
    private String  libelleProduit;

    @Schema(description = "JPA Produit: code produit",
            name="nombreCarton",
            required=false)
    private int nombreCarton;

    @Schema(description = "JPA Produit: code produit",
            name="origine",
            required=false)
    private String origine;

    @Schema(description = "JPA Produit: conteneur",
            name="conteneur",
            required=false)
    private String conteneur;

    @Schema(description = "JPA Produit: code produit",
            name="dateProduction",
            required=false)
    private Date dateProduction;

    @Schema(description = "JPA Produit: poids produit",
            name="poids",
            required=false)
    private double poids;
}
