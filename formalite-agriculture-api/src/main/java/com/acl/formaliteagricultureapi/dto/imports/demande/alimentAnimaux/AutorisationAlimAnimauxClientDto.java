package com.acl.formaliteagricultureapi.dto.imports.demande.alimentAnimaux;

import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 19/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description = "AutorisationEnlevementClientDto")
public class AutorisationAlimAnimauxClientDto {

    @Schema(description = "JPA Formalite: Personne connectée",
            name = "compteClient",
            required = true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Numéro de l'agrement importateur",
            name = "numeroAgrement",
            required = true)
    @NotNull
    private String numeroAgrement;



    @Schema(description = "JPA DetAutorisationProduit: list des autorisations des produits",
            name = "detAutorisationProduitDtos",
            required = true)
    @NotNull
    List<DetAutorisationProduitDto> detAutorisationProduitDtos;

    //Fin Det Autorisation


    /*
     * Debut File Dto
     */
   /* @Schema(description = "Dto pour la creation des fichiers et piece jointe",
            name = "autorisationEnlevementClientFileDtos",
            required = true)
    @NotNull
    private List<AutorisationEnlevementClientFileDto> autorisationEnlevementClientFileDtos;*/
    /*
     * Fin File Dto
     */

}
