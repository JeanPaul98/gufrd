package com.acl.formaliteagricultureapi.dto.exports.vegetaux.inspection;


import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetPhytosanitaireProduitAvecTraitementDto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description ="PhytosanitaireObtentionCertificatProduitAvecTraitementClientDto" )
public class PhytosanitaireObtentionCertificatProduitAvecTraitementClientDto {	
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
    
    //Debut Phytosanitaire Obtention de Certificat
    
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
    
    //Détails produits 
    
    private List<DetPhytosanitaireProduitAvecTraitementDto> detPhytosanitaireProduitDtos;
   
    
    
    

}
