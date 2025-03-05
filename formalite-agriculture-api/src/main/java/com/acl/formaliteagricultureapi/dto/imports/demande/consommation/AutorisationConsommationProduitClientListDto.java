package com.acl.formaliteagricultureapi.dto.imports.demande.consommation;

import com.acl.formaliteagricultureapi.dto.piecejointe.PieceJointeDto;
import com.acl.formaliteagricultureapi.dto.produit.DetAutorisationProduitDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Zansouyé on 21/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="AutorisationConsommationProduitClientListDto" )
public class AutorisationConsommationProduitClientListDto {

    //Debut formalite
    private Long idFormalite;
    
    @Schema(description = "JPA Formalite: Information du navire",
            name="numGenerer",
            required=true)
    @NotNull
    private String numGenerer;

    @Schema(description = "JPA Formalite: Compte client",
            name="compteClient",
            required=true)
    @NotNull
    private String compteClient;


    private String statutPaiement;

    @Schema(description = "JPA Formalite: Information du navire",
            name="atp",
            required=true)
    @NotNull
    private String atp;

    @Schema(description = "JPA Formalite: Information du navire",
            name="nomNavire",
            required=true)
    @NotNull
    private String nomNavire;

    @Schema(description = "JPA Formalite: Information du navire",
            name="imo",
            required=true)
    @NotNull
    private String imo;

    @Schema(description = "JPA Formalite: Information du navire",
            name="affreteur",
            required=true)
    @NotNull
    private String affreteur;
    

    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;


    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

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


    @Schema(description = "JPA formalite: Information sur l'autorisation",
            name="etat",
            required=true)
    @NotNull
    private String etat;

    //Fin formalite

    //Debut Autorisation


    //Fin Autorisation
    private Long idAutorisation;
    //Debut Det Autorisation

    @Schema(description = "JPA Autorisation: Motif Rejet",
            name="numeroAgrement",
            required=false)
    @NotNull
    private String numeroAgrement;


    @Schema(description = "JPA Autorisation: Motif Rejet",
            name="dateEmbarquement",
            required=false)
    @NotNull
    private Date dateEmbarquement;

    List<DetAutorisationProduitDto> detAutorisationProduitDtos;
    //Fin Det Autorisation


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="typeAutorisation",
            required=true)
    @NotNull    //Type Autorisation
    private String typeAutorisation;

    private String typeRegime;

    private String numeroAgrementTransit;

    private String statutDemande;

    List<PieceJointeDto> pieceJointeList;

    private String nif;

    private String numeroDossier;


    @Schema(description = "JPA Autorisation d'importation: ",
            name="provenance")
    @NotNull
    private String provenance;

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="refTypeAutorisation",
            required=false)
    private String refTypeAutorisation;

    private String nomImportateur;

    private String motifRejet;

    @Schema(description = "JPA Autorisation: Nom de la socité demandeur",
            name="nomSociete",
            required=false)
    private String nomSociete;



    public AutorisationConsommationProduitClientListDto(Long idFormalite, String atp,String affreteur, String nomNavire,String imo,
                                                  String chaine, Date dateDemande,
                                                  String etat, String typeAutorisation, String refTypeAutorisation, String numGenerer,
                                                  Date dateRejet, Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                  List<DetAutorisationProduitDto> detAutorisationProduitDtos) {
        this.idFormalite = idFormalite;
        this.chaine = chaine;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.numGenerer = numGenerer;
        this.typeAutorisation = typeAutorisation;
        this.refTypeAutorisation = refTypeAutorisation;
        this.dateRejet = dateRejet;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.atp =  atp;
        this.affreteur = affreteur;
        this.nomNavire = nomNavire;
        this.detAutorisationProduitDtos = detAutorisationProduitDtos;
    }

    public AutorisationConsommationProduitClientListDto(Long idFormalite, String provenance,
                                                        Date dateEmbarquement, String chaine, Date dateDemande,
                                                        String etat, String typeAutorisation,
                                                        String typeReference, String numGenerer,
                                                        Date dateRejet, Long idAutorisation,
                                                        Date dateAccepte, Date dateTraitement,
                                                        Date dateSoumission, String compteClient,
                                                        String motifRejet, String nomImportateur,
                                                        double montantRedevance,
                                                        List<DetAutorisationProduitDto> detAutorisationProduitDtoList) {
    this.idFormalite = idFormalite;
    this.provenance = provenance;
    this.dateDemande = dateDemande;
    this.etat = etat;
    this.dateEmbarquement = dateEmbarquement;
    this.numGenerer = numGenerer;
    this.typeAutorisation = typeAutorisation;
    this.compteClient = compteClient;
    this.motifRejet = motifRejet;
    this.nomImportateur = nomImportateur;
    this.montantRedevance = montantRedevance;
    this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
    this.dateRejet = dateRejet;
    this.idAutorisation = idAutorisation;
    this.dateAccepte = dateAccepte;
    this.dateTraitement = dateTraitement;
    this.dateSoumission = dateSoumission;
    this.chaine = chaine;

    }

    public AutorisationConsommationProduitClientListDto(Long idAutorisation, Long idFormalite, String atp,
                                                        String nomNavire, String immo, String affreteur,
                                                        String conteneur, Date datearrivee, String chaine,
                                                        Date dateDemande, String etat, String typeAutorisation,
                                                        String typeReference, String numGenerer, Date dateRejet,
                                                        Date dateAccepte, Date dateTraitement, Date dateSoumission,
                                                        List<DetAutorisationProduitDto> detAutorisationProduitDtoList,
                                                        String compteClient, String nomImportateur,
                                                        double montantRedevance, String motifRejet) {

        this.idFormalite = idFormalite;
        this.provenance = provenance;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.dateEmbarquement = dateEmbarquement;
        this.numGenerer = numGenerer;
        this.typeAutorisation = typeAutorisation;
        this.compteClient = compteClient;
        this.motifRejet = motifRejet;
        this.nomImportateur = nomImportateur;
        this.montantRedevance = montantRedevance;
        this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
        this.dateRejet = dateRejet;
        this.idAutorisation = idAutorisation;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.chaine = chaine;
    }
}
