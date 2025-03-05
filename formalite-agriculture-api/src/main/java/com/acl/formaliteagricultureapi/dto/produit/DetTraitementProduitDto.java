package com.acl.formaliteagricultureapi.dto.produit;

import com.acl.formaliteagricultureapi.dto.societe.SocieteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description = "DetTraitementProduitDto")
public class DetTraitementProduitDto {

    @Schema(description = "JPA Produit: code produit",
            name="produit",
            required=false)
    private ProduitDto produit;

    @Schema(description = "JPA Produit: code produit",
            name="substanceActive",
            required=false)
    private String substanceActive;

    @Schema(description = "JPA Produit: code produit",
            name="temperature",
            required=false)
    private String temperature;

    @Schema(description = "JPA Produit: ID de la societe de traitement",
            name="idSociete",
            required=false)
    private Long  idSociete;

    @Schema(description = "JPA Traitement : nature Traitement",
            name="natureTraitement",
            required=false)
    private String natureTraitement;


    @Schema(description = "JPA Produit: code produit",
            name="heureDebTrait",
            required=false)
    private Date heureDebTrait;

    @Schema(description = "JPA Produit: code produit",
            name="concentration",
            required=false)
    private String concentration;

    @Schema(description = "JPA Produit: code produit",
            name="duree",
            required=false)
    private int duree;


}
