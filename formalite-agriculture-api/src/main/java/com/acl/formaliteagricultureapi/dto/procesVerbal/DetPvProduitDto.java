package com.acl.formaliteagricultureapi.dto.procesVerbal;

/**
 * @author Zansouyé on 23/08/2024
 * @project formalite-agriculture-api
 */

import com.acl.formaliteagricultureapi.dto.produit.ProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DetPvProduitDto")
public class DetPvProduitDto {

    @Schema(description = "JPA Formalite: Produit ou nature (code Produit)",
            name="codProduit")
    private String codProduit;

    @Schema(description = "JPA Formalite: Description produit)",
            name="descriptionEnvoie")
    private String descriptionEnvoie;

    @Schema(description = "JPA Formalite: Information l'inspecteur",
            name="quantite",
            required=true)
    @NotNull
    private int quantite;

    @Schema(description = "JPA Formalite: Information origine",
            name="origine",
            required=false)
    private String origine;

    @Schema(description = "JPA Formalite: Information emplacement",
            name="emplacement")
    private String emplacement;

    @Schema(description = "JPA Formalite: Information ",
            name="mesure")
    private String mesure;

    @Schema(description = "JPA Formalite: Pharma ",
            name="moyenTransport")
    private String moyenTransport;

    @Schema(description = "JPA Formalite: Matière active du produit ",
            name="matiereActive")
    private String matiereActive;

    public DetPvProduitDto(String libelle, int quantite, String emplacement,
                           String mesure, String origine, String moyenTransport, String matiereActive) {
        this.codProduit = libelle;
        this.quantite = quantite;
        this.emplacement = emplacement;
        this.mesure = mesure;
        this.origine = origine;
        this.moyenTransport = moyenTransport;
        this.matiereActive = matiereActive;

    }

    public DetPvProduitDto(int quantite, String emplacement,
                           String mesure, String origine, String moyenTransport, String matiereActive, String descriptionEnvoie) {
        this.quantite = quantite;
        this.emplacement = emplacement;
        this.mesure = mesure;
        this.origine = origine;
        this.moyenTransport = moyenTransport;
        this.matiereActive = matiereActive;
        this.descriptionEnvoie = descriptionEnvoie;

    }
}
