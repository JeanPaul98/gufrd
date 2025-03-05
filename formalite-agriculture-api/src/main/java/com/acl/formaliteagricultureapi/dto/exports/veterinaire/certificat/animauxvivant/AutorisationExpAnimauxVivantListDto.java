package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.animauxvivant;

/**
 * @author kol on 04/09/2024
 * @project formalite-agriculture-api
 */

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetCertificatProduitDto;
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
@Schema(description = "AutorisationExpAnimauxVivantDto")
public class AutorisationExpAnimauxVivantListDto {

    @Schema(description = "JPA Formalite: Nif",
            name = "nif",
            required = true)
    @NotNull
    private String nif;

    @Schema(description = "JPA Formalite: Numero dossier du demandeur ATD",
            name="numeroDossier",
            required=true)
    @NotNull
    private String numeroDossier;

    /*
   Début formalite
    */
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
    Information de la piece jointe
     */
    @Schema(description = "JPA Produit detail",
            name = "detCertificatProduitDtos",
            required = true)
    @NotNull
    private List<PieceJointeDto> pieceJointeDtoList ;

    /*
    Fin piece jonte DTO
     */


    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;

    private String typeReference;

    private String numeroAgrement;

    private String motifRejet;

    private String numGenerer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTraitement;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRejet;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;

    private Long idFormalite;

    private Long idCertificat;

    private String statutPaiement;

    @Schema(description = "JPA formalite: Chaine Export",
            name="etat")
    private String etat;


    public AutorisationExpAnimauxVivantListDto(Long idFormalite, Long idCertificat, String compteClient, String expediteur, String
            destinataire, String identification, String chaine, Date dateDemande, String typeReference, String numGenerer, Date dateRejet, Date dateAccepte,
                                               Date dateTraitement, Date dateSoumission, String moyenTransport, String traitement, List<DetCertificatProduitDto> detCertificatProduits,
                                               String adresseExpediteur, String paysExpediteur, String paysOrigine, String lieuOrigine, String paysDestination,
                                               String lieuChargement, String posteFrontalier, Date dateDepart) {

        this.idFormalite= idFormalite;
        this.idCertificat = idCertificat;
        this.compteClient = compteClient;
        this.expediteur = expediteur;
        this.nomDestinataire = destinataire;
        this.chaine = chaine;
        this.dateDemande = dateDemande;
        this.typeReference = typeReference;
        this.numGenerer = numGenerer;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.moyenTransport = moyenTransport;
        this.detCertificatProduitDtos = detCertificatProduits;
        this.paysExpediteur = paysExpediteur;
        this.paysOrigine = paysOrigine;
        this.lieuOrigine = lieuOrigine;
        this.paysDestination = paysDestination;
        this.posteFrontalier = posteFrontalier;
        this.dateDepart = dateDepart;
        this.lieuChargement = lieuChargement;


    }
}
