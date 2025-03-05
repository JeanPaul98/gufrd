package com.acl.formaliteagricultureapi.dto.imports.cargaison;


import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Schema(description ="PhytosanitaireCargaisonClientDto" )
@ToString
public class PhytosanitaireCargaisonClientDto {

    /*
     * Debut Formalite
     */
    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    //@NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal")
    private String nif;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
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

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier")
    private String numeroDossier;



    List<PieceJointeFormaliteDto> pieceJointeDtoList ;
    /*
      Fin formalite
     */

   // Debut Inspection Phytosanitaire Cargaison

    @Schema(description = "JPA Phytosanitaire: Nom du demandeur",
            name="nomDemandeur",
            required=true)
    @NotNull
    private String nomDemandeur;

    @Schema(description = "JPA Phytosanitaire: Profession du demandeur",
            name="professionDemandeur",
            required=true)
    @NotNull
    private String professionDemandeur;

    @Schema(description = "JPA Phytosanitaire: Adresse du demandeur",
            name="adresseDemandeur",
            required=true)
    @NotNull
    private String adresseDemandeur;

    @Schema(description = "JPA Phytosanitaire: Structure du demandeur",
            name="structureDemandeur")
    private String structureDemandeur;

    @Schema(description = "JPA Phytosanitaire: lieu de l'inspection",
            name="lieuInspection",
            required=true)
    @NotNull
    private String lieuInspection;

    @Schema(description = "Numéro BL",
            name="numeroBl")
    private String numeroBl;

    @Schema(description = "JPA Phytosanitaire: Date prevu inspection",
            name="datePrevuInspection")
    private Date datePrevuInspection;

    @Schema(description = "JPA Detail phytosanitaire Produit: Designation produit , quantite",
            name="detPhytosanitaireProduitDtos")
    private List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos;
     

    
}
