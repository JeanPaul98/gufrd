package com.acl.formaliteagricultureapi.dto.imports.demande.medicament;


import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Jules on 21/08/2024
 * @project formalite-agriculture-api
 */

@Getter
@Setter
@Schema(description ="AutorisationEnlevementMedicamentClientDto" )
public class AutorisationEnlevementMedicamentClientDto {

    /*
     * Debut Formalite
     */
    @Schema(description = "JPA Formalite: Information du navire",
            name="atp")
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire")
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo")
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur")
    private String affreteur;

    @Schema(description = "JPA Formalite: Utilisateur connecté",
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
     * Debut autorisation d'importation et d'enlèvement de médicaments vétérinaire
     */
    
    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="conteneur",
            required=true)
    @NotNull
    private String conteneur;

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="dateembarquement",
            required=true)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateembarquement;

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="datearrivee",
            required=true)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date datearrivee;


    /*
    Fin Autorisation 
     */

    /*
    Details Autorisation Produit
     */
    @Schema(description = "JPA Detail autorisation Produit: Designation produit , quantite",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;
}
