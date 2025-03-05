package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeFormaliteDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@ToString
@Schema(description = "AutorisationExpAnimauxVivantDto")
public class AutorisationExpAnimauxVivantDto {

    /*
   Début formalite
    */
    @Schema(description = "JPA Formalite: Personne connectée",
            name = "compteClient",
            required = true)
    @NotNull
    private String compteClient;

    @Schema(description = "JPA Formalite: Nif de la societé demandeur",
            name = "nif",
            required = true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Numéro agrément",
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

    @Schema(description = "JPA Certificat : pays expéditeur",
            name = "paysExpediteur",
            required = true)
    @NotNull
    private String paysExpediteur ;

    @Schema(description = "JPA Certificat : expéditeur",
            name = "expediteur",
            required = true)
    @NotNull
    private String expediteur ;

    @Schema(description = "JPA Certificat : pays Origine",
            name = "paysOrigine",
            required = true)
    @NotNull
    private String paysOrigine ;

    @Schema(description = "JPA Certificat : pays Origine",
            name = "lieuOrigine",
            required = true)
    @NotNull
    private String lieuOrigine ;

    @Schema(description = "JPA Certificat : pays d'estination",
            name = "paysDestination",
            required = true)
    @NotNull
    private String paysDestination ;

    @Schema(description = "JPA Certificat : Nom destinataire ",
            name = "nomDestinataire",
            required = true)
    @NotNull
    private String nomDestinataire ;

    @Schema(description = "JPA Certificat : Lieu expédition",
            name = "lieuChargement",
            required = true)
    @NotNull
    private String lieuChargement ;

    @Schema(description = "JPA Certificat : Traitement",
            name = "dateDepart",
            required = true)
    @NotNull
    private Date dateDepart ;

    @Schema(description = "JPA Certificat : Traitement",
            name = "posteFrontalier",
            required = true)
    @NotNull
    private String posteFrontalier ;

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
    List<PieceJointeFormaliteDto> pieceJointeFormaliteDtos;

    /*
    Fin certificat produit
     */

}
