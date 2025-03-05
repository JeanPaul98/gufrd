package com.acl.formaliteagricultureapi.dto.imports.depotage;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 19/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
public class AutorisationDepotageTransitClientDto {

    @Schema(description = "JPA Formalite: Numéro d'identification fiscal",
            name="nif")
    private String nif;

    //Debut formalite
    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier")
    private String numeroDossier;

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

    @Schema(description = "JPA Formalite: compte client",
            name="compteClient")
    private String compteClient;

    @Schema(description = "JPA Formalite: Numéro d'autorisation",
            name = "numeroAutorisation")
    private String numeroAutorisation;


    @Schema(description = "JPA Formalite: Nom importateur",
            name = "nomImportateur")
    private String nomImportateur;


    //Fin formalite

    //Debut Autorisation depotage

    @Schema(description = "JPA DetAutorisationProduit: Date d'arrivée",
            name="datearrivee",
            required=true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datearrivee;

    @Schema(description = "JPA BL: BL POUR LES MARCHANDISES",
            name="numeroBL")
    private String numeroBL;

    // Fin autorisation depotage

    /**
     * Debut DetAutorisation Produit
     */
    @Schema(description = "JPA DetAutorisationProduit: list des autorisations des produits",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;

    /*
    Piece jointe
     */
    List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;
    /*
    Fin de piece jointe
     */
}
