package com.acl.formaliteagricultureapi.dto.autorisation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 18/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description ="NotificationConsignationDto" )
public class NotificationConsignationDto {

    /*
    *   Debut Formalite
     */
    private Long idFormalite;
    /*
    *   Fin Formalite
     */

    /*
     * Début Inspecteur
     */
        @Schema(description = "JPA Inspecteur",
                name="idInspecteur",
                required=true)
        @NotNull
        private Long idInspecteur;
    /*
     * Fin Inspecteur
     */

    /*
    *   Debut Notification Consignation
     */
        @Schema(description = "JPA Notification Consignation",
                name="dateConsignation",
                required=true)
        @NotNull
        private Date dateConsignation;

        @Schema(description = "JPA Notification Consignation",
                name="motifConsignation",
                required=true)
        @NotNull
        private String motifConsignation;

        @Schema(description = "JPA Notification Consignation",
                name="lieuConsignation",
                required=true)
        @NotNull
        private String lieuConsignation;

        @Schema(description = "JPA Notification Consignation",
                name="analyseDemande",
                required=true)
        @NotNull
        private String analyseDemande;

        @Schema(description = "JPA Notification Consignation",
                name="numeroAutorisation",
                required=true)
        @NotNull
        private String numeroAutorisation;

        @Schema(description = "JPA Notification Consignation",
                name="moyenTransport",
                required=true)
        @NotNull
        private String moyenTransport;

        @Schema(description = "JPA Notification Consignation",
                name="reference",
                required=true)
        @NotNull
        private String reference;

    /*
    * Fin Notification Consignation
     */


    /*
    * Debut DetNotificationConsignationProduit
     */

        List<DetNotificationConsignationProduitDto> detNotificationConsignationProduitDtoList;

    /*
    * Fin DetNotificationConsignationProduit
     */

}
