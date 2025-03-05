package com.acl.formaliteagricultureapi.dto.imports.demande.animauxVivant;


import java.util.Date;
import java.util.List;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
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
@Schema(description ="AutorisationAnimauxVivantsClientDto" )
public class AutorisationAnimauxVivantsClientDto {

    /*
     Debut formalite
     */
    @Schema(description = "JPA Formalite: Personne connectée",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Numero agrement",
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

    @Schema(description = "JPA Formalite: Provenance de la marchandise",
            name="provenance",
            required=true)
    @NotNull
    private String provenance;

    /*
    Details Autorisation Produit
     */
    @Schema(description = "JPA Detail autorisation Produit: Designation produit , quantite",
            name="detAutorisationProduitDtos")
    private List<DetAutorisationProduitDto> detAutorisationProduitDtos;
}
