package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description = "AutorisationExpCireDto")
public class AutorisationExpCireDto {


    @Schema(description = "JPA Formalite: Nif de l'entreprise ",
            name = "nif",
            required = true)
    @NotNull
    private String nif;


    /*
    Début formalite
     */
    @Schema(description = "JPA Formalite: Personne connectée",
            name = "compteClient",
            required = true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Numéro agrement",
            name = "numeroAgrement",
            required = true)
    @NotNull
    private String numeroAgrement;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;
    /*
        Fin formalite
     */

    /*
    Debut Certificat
     */

    @Schema(description = "JPA Certificat : moyen de transport",
            name = "moyenTransport",
            required = true)
    @NotNull
    private String moyenTransport ;

    @Schema(description = "JPA Certificat : Lieu expédition",
            name = "expediteur",
            required = true)
    @NotNull
    private String expediteur ;

    @Schema(description = "JPA Certificat : Lieu expédition",
            name = "lieuExpedition",
            required = true)
    @NotNull
    private String lieuExpedition ;



    @Schema(description = "JPA Certificat : (Transport : port)",
            name = "adresseExpediteur",
            required = true)
    @NotNull
    private String adresseExpediteur ;

    @Schema(description = "JPA Certificat : Traitement",
            name = "destinataire",
            required = true)
    @NotNull
    private String destinataire ;


    @Schema(description = "JPA Certificat : expéditeur",
            name = "adresseDestinataire",
            required = true)
    @NotNull
    private String adresseDestinataire ;

    @Schema(description = "JPA Certificat : destinataire",
            name = "lieuDestination",
            required = true)
    @NotNull
    private String lieuDestination ;

    @Schema(description = "JPA Certificat : Lieu de chargement",
            name = "lieuDeChargement",
            required = true)
    @NotNull
    private String lieuDeChargement ;


    /*
    Fin certificat
     */

    /*
    Information Produit
     */
    @Schema(description = "JPA Produit detail",
            name = "detCertificatProduitDtos",
            required = true)
    @NotNull
    private List<DetCertificatProduitDto> detCertificatProduitDtos;

    /*
    Fin certificat produit
     */


    /*
 Information Produit
  */
    @Schema(description = "JPA Produit detail",
            name = "pieceJointeFormaliteDtos",
            required = true)
    @NotNull
    private List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;

    /*
    Fin certificat produit
     */


}
