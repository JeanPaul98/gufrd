package com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection;

import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 25/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description ="InspectionPhytoObtentionCertifDto" )
public class InspectionPhytoObtentionCertifDto {

    /*
    Début formalité
    Information navire
     */
    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo")
    private String imo;

    @Schema(description = "JPA Formalite: Information de l'affreteur",
            name="affreteur")
    private String affreteur;

    /*
    Fin formalite
     */

    /*
    Debut Inspection pour Obtention certificat
     */
    @NotNull
    private String lieuDestination;

    @NotNull
    private String adresseExpediteur;

    @NotNull
    private String nomExpediteur;

    @NotNull
    private String nomDestinataire;

    @NotNull
    private String adresseDestinataire;

    @NotNull
    private String moyenTransport;

    @NotNull
    private Date dateHeureEmbarquement;

    private String renseignementComplementaire;

    private boolean traitementProduit;

    /*
    Debut détail produit denrée
     */
    private List<DetPhytosanitaireProduitDto> detPhytosanitaireProduitDtos;

    /*
    Information traitement du produit
     */

    private List<DetTraitementProduitDto> detTraitementProduitDtos;

    /*
        Fin détail traitement produit
     */

}
