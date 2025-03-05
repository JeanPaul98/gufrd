package com.acl.formaliteagricultureapi.dto.procesVerbal;

import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import com.acl.formaliteagricultureapi.models.DetProcesVerbalProduit;
import com.acl.formaliteagricultureapi.models.DetTraitementProduit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 23/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description = "PvPhytoSanitaireCargConteneurDto")
public class PvPhytoSanitaireCargConteneurDto {

    @Schema(description = "JPA Formalite: Id de la formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;


    @Schema(description = "JPA Formalite: Information du navire",
            name="pointEntree",
            required=true)
    @NotNull
    private String pointEntree;

    @Schema(description = "JPA Formalite: Information du navire",
            name="lieuInspection",
            required=true)
    @NotNull
    private String lieuInspection;


    @Schema(description = "Date application",
            name="dateApplication",
            required=true)
    @NotNull
    private Date dateApplication;

    @Schema(description = "Date arrivé",
            name="datearrivee",
            required=true)
    @NotNull
    private Date datearrivee;

    @Schema(description = "Date PV",
            name="datePv",
            required=true)
    @NotNull
    private Date datePv;

    @Schema(description = "JPA Formalite: Information du navire",
            name="dateInspection",
            required=true)
    @NotNull
    private Date dateInspection;

    @Schema(description = "JPA Formalite: Information du navire",
            name="expediteur",
            required=true)
    @NotNull
    private String expediteur;

    @Schema(description = "JPA Formalite: Information du navire",
            name="destinataire",
            required=true)
    @NotNull
    private String destinataire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="numeroEnregistrement",
            required=true)
    @NotNull
    private String numeroEnregistrement;


    @Schema(description = "JPA Formalite: Information du navire",
            name="detPvProduitDtos",
            required=true)
    @NotNull
    private List<DetPvProduitDto> detPvProduitDtos;

    @Schema(description = "JPA Formalite: Information du navire",
            name="detTraitementProduitDtos")
    private List<DetTraitementProduitDto> detTraitementProduitDtos;

    //Controle documentaire

    @Schema(description = "JPA Formalite: Information du navire",
            name="controleTechnique",
            required=true)
    @NotNull
    private String controleTechnique;


    @Schema(description = "JPA Formalite: Information du navire",
            name="resultatInspection",
            required=true)
    @NotNull
    private String resultatInspection;

    @Schema(description = "JPA Formalite: Remarque",
            name="remarque")
    @NotNull
    private String remarque;


//Inspecteur

    @Schema(description = "JPA Formalite: Information du navire",
            name="detPVInspecteurDtos",
            required=true)
    @NotNull
    private  List<DetPVInspecteurDto> detPVInspecteurDtos;


    @Schema(description = "JPA Formalite: Information du navire",
            name="agentPv")
    @NotNull
    private String agentPv;

}
