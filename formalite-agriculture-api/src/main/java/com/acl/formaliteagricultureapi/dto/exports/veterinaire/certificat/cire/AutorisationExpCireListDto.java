package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cire;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
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
@Schema(description = "AutorisationExpCireListDto")
public class AutorisationExpCireListDto {

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

    @Schema(description = "JPA Certificat : Lieu expédition",
            name = "expediteur",
            required = true)
    @NotNull
    private String expediteur ;


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

    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;

    private String numeroAgrementTransit;

    private String motifRejet;

    List<PieceJointeDto> pieceJointeList;

    private String nif;

    private String numeroDossier;

    private String numeroAgrement;

    private String typeReference;

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

    @Schema(description = "JPA formalite: Chaine Export",
            name="etat")
    private String etat;

    private String statutPaiement;

    public AutorisationExpCireListDto(Long idFormalite, String destinataire,
                                      String expediteur, String compteClient,
                                       String etat, String chaine, Long idCertificat,
                                      String numGenerer, Date dateDemande, Date dateAccepte,
                                      String lieuDeChargement, Date dateSoumission,
                                       Date dateTraitement, List<DetCertificatProduitDto> detCertificatProduitList,String moyenTransport,
                                      String lieuDeChargements, String adresseDestinataire,
                                      String adresseExpediteur, String lieuDestination) {


   this.idFormalite = idFormalite;
   this.destinataire = destinataire;
   this.expediteur = expediteur;
   this.compteClient = compteClient;
   this.etat =etat;
   this.chaine = chaine;
   this.idCertificat = idCertificat;
   this.numGenerer = numGenerer;
   this.dateDemande = dateDemande;
   this.dateSoumission = dateSoumission;
   this.dateTraitement = dateTraitement;
   this.dateAccepte = dateAccepte;
   this.detCertificatProduitDtos = detCertificatProduitList;
   this.moyenTransport = moyenTransport;
   this.lieuDeChargement = lieuDeChargements;
   this.adresseDestinataire = adresseDestinataire;
   this.adresseExpediteur = adresseExpediteur;
   this.lieuDestination = lieuDestination;

    }
}
