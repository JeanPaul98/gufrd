package com.acl.formaliteagricultureapi.dto.imports.navire;


import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Schema(description ="PhytosanitaireNavireClientDto" )
public class PhytosanitaireNavireClientDto {

    /*
     * Debut Formalite
     */
    @Schema(description = "JPA Formalite: Champ texte pour renseigner le numéro d’Annonce de Transport Physique",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal",
            name="nif",
            required=true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire",
            required=true)
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo",
            required=true)
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur")
    private String affreteur;

    @Schema(description = "JPA Formalite: Information Compte client",
            name="compteClient")
    private String compteClient;


    /*
      Fin formalite
     */
    
    /*
     * Debut Inspection Phytosanitaire de Navire
     */

    @Schema(description = "JPA Inspection Phytosanitaire de Navire",
            name="nomDemandeur",
            required=true)
    @NotNull
    private String nomDemandeur;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire",
            name="professionDemandeur",
            required=true)
    @NotNull
    private String professionDemandeur;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire",
            name="adresseDemandeur",
            required=true)
    @NotNull
    private String adresseDemandeur;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire",
            name="lieuInspection",
            required=true)
    @NotNull
    private String lieuInspection;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire",
            name="datePrevueInspection",
            required=true)
    @NotNull
    private Date datePrevueInspection;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire: structure",
            name="structureDemandeur",
            required=true)
    @NotNull
    private String structureDemandeur;

    @Schema(description = "JPA Inspection Phytosanitaire de Navire: type Cargaison",
            name="typeCargaison",
            required=true)
    @NotNull
    private String typeCargaison;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;

    /*
       Fin Inspection Phytosanitaire de Navire
     */

    /*
    Fichier pièce jointe
     */

    List<PieceJointeFormaliteDto> pieceJointeDtoList ;

    /*
    Fin pièce jointe
     */
}
