package com.acl.formaliteagricultureapi.dto.imports.demande.consommation;


import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description ="AutorisationConsommationProduitClientDto" )
public class AutorisationConsommationProduitClientDto {

    /*
     * Debut Formalite
     */
	

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

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
    

    @Schema(description = "JPA Formalite: Information du compteClient",
            name="compteClient")
    private String compteClient;

    @Schema(description = "JPA Formalite: Information sur l'importateur",
            name = "nomImportateur",
            required = true)
    @NotNull
    private String nomImportateur;


    /*
      Fin formalite
     */
    
    /*
     * Debut Autorisation d'importation de produits en consommation local
     */

    @Schema(description = "JPA Autorisation d'importation: ",
            name="provenance")
    @NotNull
    private String provenance;

    @Schema(description = "JPA Autorisation d'importation: ",
            name="datearrivee")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date datearrivee;

    /*
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateembarquement;

     */
    
    /*
    Fin Autorisation d'importation de produits en consommation local
     */

    /*
    Details Autorisation Produit
     */
    @Schema(description = "JPA Detail autorisation Produit: Designation produit , quantite",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;
}
