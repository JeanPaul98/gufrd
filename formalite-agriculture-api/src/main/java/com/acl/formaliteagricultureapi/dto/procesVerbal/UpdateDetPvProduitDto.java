package com.acl.formaliteagricultureapi.dto.procesVerbal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kol on 10/4/24
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "UpdateDetPvProduitDto")
public class UpdateDetPvProduitDto {

    @Schema(description = "JPA Formalite: Produit ou nature (code Produit)",
            name="libelle")
    private String libelle;

    @Schema(description = "JPA Formalite: Description envoie (description envoie)",
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
}
