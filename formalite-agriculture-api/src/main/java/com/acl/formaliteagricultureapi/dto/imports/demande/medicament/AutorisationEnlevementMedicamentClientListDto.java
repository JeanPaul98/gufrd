package com.acl.formaliteagricultureapi.dto.imports.demande.medicament;

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
 * @author Jules on 21/08/2024
 * @project formalite-agriculture-api
 */
@Getter
@Setter
@Schema(description ="AutorisationEnlevementMedicamentClientListDto" )
public class AutorisationEnlevementMedicamentClientListDto {

    //Debut formalite
    private Long idFormalite;
    
    @Schema(description = "JPA Formalite: Information du navire",
            name="numGenerer",
            required=true)
    @NotNull
    private String numGenerer;


    @Schema(description = "JPA Formalite: Montant de la redevance",
            name="montantRedevance")
    @NotNull
    private double montantRedevance;


    @Schema(description = "JPA Formalite: Information du navire",
            name="chaine",
            required=true)
    private String chaine;

    @Schema(description = "JPA Formalite: Nom de la societe",
            name="nomSociete",
            required=true)
    private String nomSociete;

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

    @Schema(description = "JPA Autorisation: Numéro agrement",
            name="numeroAgrement",
            required=false)
    @NotNull
    private String numeroAgrement;

    @Schema(description = "JPA Autorisation: Provenance",
            name="provenance",
            required=false)
    @NotNull
    private String provenance;


    @Schema(description = "JPA Autorisation: Motif Rejet",
            name="dateEmbarquement",
            required=false)
    @NotNull
    private Date dateEmbarquement;

    private String typeRegime;

    private String numeroAgrementTransit;

    private String statutDemande;

    private String nif;

    private String numeroDossier;

    List<PieceJointeDto> pieceJointeList;
    //Fin Autorisation

    //Debut Det Autorisation

    private Long idAutorisation;

    List<DetAutorisationProduitDto> detAutorisationProduitDtos;
    //Fin Det Autorisation


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="typeAutorisation",
            required=true)
    @NotNull    //Type Autorisation
    private String typeAutorisation;

    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="typeAutorisation",
            required=false)
    private String nomImportateur;

    @Schema(description = "JPA Autorisation: Information Motif Rejet",
            name="motifRejet",
            required=false)
    private String motifRejet;


    @Schema(description = "JPA Autorisation: Information sur l'autorisation",
            name="refTypeAutorisation",
            required=true)
    @NotNull
    private String refTypeAutorisation;

    private String compteClient;

    private String statutPaiement;


    public AutorisationEnlevementMedicamentClientListDto(Long idFormalite, String atp, String nomNavire,
                                                         String immo, String affreteur,
                                                         String conteneur, Date datearrivee, String chaine, Date dateDemande,
                                                         String etat, String typeAutorisation, String refTypeAutorisation, String numGenerer,
                                                         Date dateRejet, Long idAutorisation, Date dateAccepte, Date dateTraitement, Date dateSoumission,
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
        this.idAutorisation = idAutorisation;
        this.dateSoumission = dateSoumission;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.detAutorisationProduitDtos = detAutorisationProduitDtos;
    }

    public AutorisationEnlevementMedicamentClientListDto(Long idFormalite, String provenance,
                                                         Date dateEmbarquement, String chaine,
                                                         Date dateDemande, String etat,
                                                         String typeAutorisation, String typeReference,
                                                         String numGenerer, Date dateRejet,
                                                         Long idAutorisation, Date dateAccepte,
                                                         Date dateTraitement, Date dateSoumission,
                                                         String compteClient, String motifRejet, double montantRedevance,
                                                         List<DetAutorisationProduitDto> detAutorisationProduitDtoList) {

    this.idFormalite = idFormalite;
    this.provenance = provenance;
    this.dateEmbarquement = dateEmbarquement;
    this.chaine = chaine ;
    this.dateDemande = dateDemande;
    this.etat = etat;
    this.typeAutorisation = typeAutorisation;
    this.refTypeAutorisation = typeReference;
    this.numGenerer = numGenerer;
    this.dateRejet = dateRejet;
    this.idAutorisation = idAutorisation;
    this.dateAccepte = dateAccepte;
    this.dateTraitement = dateTraitement;
    this.dateSoumission = dateSoumission;
    this.compteClient = compteClient;
    this.motifRejet = motifRejet;

    this.montantRedevance = montantRedevance;
    this.detAutorisationProduitDtos = detAutorisationProduitDtoList;
    }

    public AutorisationEnlevementMedicamentClientListDto(
            Long idAutorisation, Long idFormalite, String atp, String nomNavire, String immo, String affreteur,
            String conteneur, Date datearrivee, String chaine, Date dateDemande, String etat,
            String typeAutorisation, String typeReference, String numGenerer, Date dateRejet,
            Date dateAccepte, Date dateTraitement, Date dateSoumission,
            List<DetAutorisationProduitDto> detAutorisationProduitDtoList, String compteClient,
            String nomImportateur, double montantRedevance, String motifRejet) {

        this.idFormalite = idFormalite;
        this.provenance = provenance;
        this.dateEmbarquement = dateEmbarquement;
        this.chaine = chaine ;
        this.dateDemande = dateDemande;
        this.etat = etat;
        this.typeAutorisation = typeAutorisation;
        this.refTypeAutorisation = typeReference;
        this.numGenerer = numGenerer;
        this.dateRejet = dateRejet;
        this.idAutorisation = idAutorisation;
        this.dateAccepte = dateAccepte;
        this.dateTraitement = dateTraitement;
        this.dateSoumission = dateSoumission;
        this.compteClient = compteClient;
        this.motifRejet = motifRejet;

        this.montantRedevance = montantRedevance;
        this.detAutorisationProduitDtos = detAutorisationProduitDtoList;

    }
}
