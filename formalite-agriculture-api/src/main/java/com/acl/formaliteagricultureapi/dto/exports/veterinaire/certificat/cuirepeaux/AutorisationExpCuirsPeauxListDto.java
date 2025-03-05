package com.acl.formaliteagricultureapi.dto.exports.veterinaire.certificat.cuirepeaux;

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
@Schema(description = "AutorisationExpCuirePeauxDto")
public class AutorisationExpCuirsPeauxListDto {

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
    Complement formalite
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

    private String numeroAgrement;

    private String nif;

    private String numeroDossier;

    List<PieceJointeDto> pieceJointeList;

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

    private String motifRejet;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccepte;

    private Long idFormalite;

    private Long idCertificat;

    private String statutPaiement;

    @Schema(description = "JPA formalite: Chaine Export",
            name="etat")
    private String etat;

    public AutorisationExpCuirsPeauxListDto(Long idFormalite, Long idCertificat, String compteClient,
                                            String expediteur, String destinataire,
                                            String identification,
                                            String chaine, Date dateDemande,
                                            String typeReference, String numGenerer, Date dateRejet,
                                            Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                            String moyenTransport, String traitement,
                                            List<DetCertificatProduitDto> detCertificatProduits, double montantRedevance,
                                            String lieuExpedition, String etat) {

        this.idFormalite = idFormalite;
        this.idCertificat = idCertificat;
        this.compteClient = compteClient;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.identification = identification;
        this.chaine = chaine;
        this.numGenerer = numGenerer;
        this.dateDemande = dateDemande;
        this.typeReference = typeReference;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.detCertificatProduitDtos = detCertificatProduits;
        this.moyenTransport = moyenTransport;
        this.traitement = traitement;
        this.montantRedevance = montantRedevance;
        this.lieuExpedition = lieuExpedition;
        this.etat = etat;
    }


    /*
    Fin complement formalite
     */
}
