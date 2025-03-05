package com.acl.formaliteagricultureapi.dto.imports.cargaison;

import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 26/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="PhytosanitaireCargaisonClientDto" )
public class PhytosanitaireCargaisonUpClientDto {

    /*
     * Debut Formalite
     */

    @Schema(description = "JPA Formalite: Information id Formalite",
            name="idFormalite")
    //@NotNull
    private Long idFormalite;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    //@NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
    //@NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo")
    //@NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur")
    private String affreteur;

    @Schema(description = "JPA Formalite: Personne connectée",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    /*
      Fin formalite
     */

    // Debut Inspection Phytosanitaire de Navire

    @Schema(description = "JPA Phytosanitaire: ",
            name="nomDemandeur",
            required=true)
    @NotNull
    private String nomDemandeur;

    @Schema(description = "JPA Phytosanitaire: ",
            name="professionDemandeur",
            required=true)
    @NotNull
    private String professionDemandeur;

    @Schema(description = "JPA Phytosanitaire: ",
            name="adresseDemandeur",
            required=true)
    @NotNull
    private String adresseDemandeur;

    @Schema(description = "JPA Phytosanitaire: ",
            name="lieuInspection",
            required=true)
    @NotNull
    private String lieuInspection;

    @Schema(description = "JPA Phytosanitaire: ",
            name="datePrevueInspection",
            required=true)
    @NotNull
    private Date datePrevueInspection;

    @Schema(description = "JPA Phytosanitaire: id Phytosanitaire  ",
            name="idPhytosanitaire",
            required=true)
    @NotNull
    private Long idPhytosanitaire;


    //Fin Inspection Phytosanitaire de Navire


    //Detail des produits

    @Schema(description = "JPA Detail phytosanitaire Produit: Designation produit , quantite",
            name="detPhytosanitaireProduitDtos")
    private List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos;


}
