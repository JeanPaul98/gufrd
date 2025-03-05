package com.acl.formaliteagricultureapi.dto.autorisation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zansouyé on 18/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description ="DetNotificationConsignationProduitDto" )
public class DetNotificationConsignationProduitDto {

    /*
    * Debut Produit
     */
        @Schema(description = "JPA Notification Produit",
                name="idProduit",
                required=true)
        @NotNull
        private Long idProduit;
    /*
    * Fin Produit
     */

    /*
    * Debut DetNotificationConsignationProduit
     */

        @Schema(description = "JPA Notification DetNotificationConsignationProduit",
                name="provenance",
                required=true)
        @NotNull
        private String provenance;

        @Schema(description = "JPA Notification DetNotificationConsignationProduit",
                name="origine",
                required=true)
        @NotNull
        private String origine;

        @Schema(description = "JPA Notification DetNotificationConsignationProduit",
                name="destination",
                required=true)
        @NotNull
        private String destination;

        @Schema(description = "JPA Notification DetNotificationConsignationProduit",
                name="quantite",
                required=true)
        @NotNull
        private int quantite;

        @Schema(description = "JPA Notification DetNotificationConsignationProduit: Poids",
                name="nombre",
                required=true)
        @NotNull
        private int nombre;

        @Schema(description = "JPA Notification DetNotificationConsignationProduit: Unite",
                name="unite",
                required=true)
        @NotNull
        private String unite;

    /*
    *   Fin DetNotificationConsignationProduit
     */
}
