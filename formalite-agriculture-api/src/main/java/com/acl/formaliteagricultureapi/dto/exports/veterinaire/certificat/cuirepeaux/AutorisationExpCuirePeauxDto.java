package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import com.acl.formaliteagricultureapi.models.DetCertificatProduit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description = "AutorisationExpCuirePeauxDto")
public class AutorisationExpCuirePeauxDto {

    /*
    Début formalite
     */

    @Schema(description = "JPA Formalite: Nif de l'utilisateur",
            name = "nif",
            required = true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Personne connectée",
            name = "compteClient",
            required = true)
    @NotNull
    private String compteClient;
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
            name = "lieuExpedition",
            required = true)
    @NotNull
    private String lieuExpedition ;

    @Schema(description = "JPA Certificat : (Transport : port)",
            name = "identification",
            required = true)
    @NotNull
    private String identification ;

    @Schema(description = "JPA Certificat : Traitement",
            name = "traitement",
            required = true)
    @NotNull
    private String traitement ;

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

    @Schema(description = "JPA Certificat : expéditeur",
            name = "expediteur",
            required = true)
    @NotNull
    private String expediteur ;

    @Schema(description = "JPA Certificat : destinataire",
            name = "destinataire",
            required = true)
    @NotNull
    private String destinataire ;

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
    @Schema(description = "JPA Piece jointe detail",
            name = "pieceJointeFormaliteDtos",
            required = true)
    @NotNull
    private List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;

    /*
    Fin certificat produit
     */



}
