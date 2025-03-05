package com.acl.formaliteagricultureapi.dto.autorisation.demande;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 24/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="AutorisationImportationUpdateDto" )
public class AutorisationImportationUpdateDto {

    /*
  Debut formalite
  */

    @Schema(description = "JPA Formalite: Id de la formalite",
            name="idFormalite",
            required=true)
    @NotNull
    private Long idFormalite;

    @Schema(description = "JPA Formalite: Personne connectée",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Numero agreement",
            name="numeroAgrement",
            required=true)
    @NotNull
    private String numeroAgrement;

    /*
      Fin formalite
     */

    /*
     * Debut autorisation
     */

    @Schema(description = "JPA Formalite: Id de l'autorisation",
            name="idAutorisation",
            required=true)
    @NotNull
    private Long idAutorisation;

    @Schema(description = "JPA Formalite: Provenance de la marchandise",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    @Schema(description = "JPA Formalite: Provenance Date d'embarquement",
            name="dateEmbarquement",
            required=true)
    @NotNull
    private Date dateEmbarquement;

    /*
     * Fin autorisation
     */

    /*
    Details Autorisation Produit
     */
    @Schema(description = "JPA Detail autorisation Produit: Designation produit , quantite, poids",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;


}
