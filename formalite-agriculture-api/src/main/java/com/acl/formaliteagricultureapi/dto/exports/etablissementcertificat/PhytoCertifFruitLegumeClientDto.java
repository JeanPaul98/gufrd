package com.acl.formaliteagricultureapi.dto.exports.etablissementcertificat;


import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitAvecTraitementDto;


import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetTraitementProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description ="DTO de phyto certif Fruit et légume client" )
public class PhytoCertifFruitLegumeClientDto {
	/*
     * Debut Formalite
     */
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


    @Schema(description = "JPA Formalite: Compte client connecté",
            name="compteClient")
    private String compteClient;
    
    //Debut Phytosanitaire Obtention de Certificat
    
    @NotNull
    private String lieuExpedition;
    
    @NotNull
    private String adresseExpediteur;
    
    @NotNull
    private String nomExpediteur;
    
    @NotNull
    private String adresseEtablissement;
    
    @NotNull
    private String numeroAgrement;

    @NotNull
    private String moyenTransport;

    private String paysLieuDestination;

    private String nomDestinataire;

    private String adresseDestinataire;

    private String remarqueParticuliere;
    
    //Détails produits traitement
    
    private List<DetTraitementProduitDto> detTraitementProduitDtos;
   
    //Détails Produit

    private List<DetCertificatProduitDto> detCertificatProduitDtos;
    
    

}
